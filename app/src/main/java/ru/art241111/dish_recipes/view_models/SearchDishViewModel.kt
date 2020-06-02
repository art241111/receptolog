package ru.art241111.dish_recipes.view_models

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import ru.art241111.dish_recipes.R
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.managers.NetManager
import ru.art241111.dish_recipes.models.DishRepository
import ru.art241111.dish_recipes.view_models.protocols.*
import ru.art241111.kotlinmvvm.extensionFunctions.plusAssign

/**
 * ViewModel to work with data and searchDishActivity
 * @author Artem Geraimov.
 */
class SearchDishViewModel(application: Application)
                             : AndroidViewModel(application),
                               UpdateFavorite, AddIngredient,
                               DeleteIngredient, LoadDishesFromRemoteRepository,
                               LoadFavoriteDishes {
    // Array of dishes.
    val dishes = MutableLiveData<Set<FullDish>>()
    private var localDishCollection: MutableSet<FullDish> = mutableSetOf()

    // Array of ingredients.
    val ingredients = ArrayList<String>()

    // Check data is loading or not.
    val isLoading = ObservableField(false)
    val warningText = ObservableField("")
    var areThereAnyOtherRecipes = true

    // spinner position
    var spinnerPosition = 0

    // Data repository.
    private val netManager = NetManager(getApplication())
    private val dishRepository: DishRepository = DishRepository(netManager)

    // TODO: Read about Disposable
    private val compositeDisposable = CompositeDisposable()

    // start page to load data
    private var startPosition = 0

    /**
     * Update favorite status in collections
     */
    override fun updateFavoriteAtOneDish(dish: FullDish) {
        replaceFavoriteStatusInCollection(dishes.value?.toMutableSet(), dish)
        replaceFavoriteStatusInCollection(localDishCollection, dish)
    }

    /**
     * Delete observer's.
     */
    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    /**
     * Add ingredient to ingredients array.
     */
    override fun addIngredient(ingredient: String) {
        ingredients.add(ingredient)
    }

    /**
     * Remove ingredient to ingredients array.
     */
    override fun deleteIngredient(ingredient: String) {
        ingredients.remove(ingredient)
    }

    /**
     * load dishes from repositories.
     */
    override fun loadFavoriteDishes() {
        dishes.value = dishRepository.getAllFavoriteDishes().toSet()
    }

    /**
     * Load dishes when screen create.
     * If the screen loads for the first time, we request data.
     * If the data is already there, then we load the old ones.
     */
    override fun loadDishesWhenScreenCreate(){
        if(localDishCollection.isEmpty()){
            loadNewDishes()
        } else{
            dishes.value = localDishCollection
        }
    }

    /**
     * Load new data, when data on screen end
     */
    override fun loadDishesWhenDataEnd(){
        if(netManager.isConnectedToInternet){
            if(areThereAnyOtherRecipes){
                startPosition += 11
                loadDishes()
            }
        } else{
            setWarningText(R.string.no_internet_connection)
        }
    }

    /**
     * Load new data, when user enter new ingredient
     */
    override fun loadNewDishes(){
        if(netManager.isConnectedToInternet){
            areThereAnyOtherRecipes = true
            startPosition = 0
            localDishCollection = createEmptyCollection()

            loadDishes()
        } else{
            setWarningText(R.string.no_internet_connection)
            dishes.value = createEmptyCollection()
        }
    }

    /**
     * load dishes from repositories.
     */
    private fun loadDishes() {
        setEmptyWarningText()
        isLoading.set(true)

        compositeDisposable += dishRepository
                .getDishes(ingredients, startPosition.toString(), spinnerPosition)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<FullDish>>() {
                    override fun onError(e: Throwable) {
                        Log.e("SearchDishViewModel.kt",
                                "Error with reading data + ${e.message}")
                        setWarningText(R.string.error_with_read_data)

                        loadDishes()
                    }

                    override fun onNext(data: List<FullDish>) {
                        if(data.isEmpty()) {
                            setWarningText(R.string.no_recipes_with_this_ingredient_were_found)
                        }
                        localDishCollection.addAll(data)
                        dishes.value = localDishCollection

                        if(data.size < 10) areThereAnyOtherRecipes = false
                    }

                    override fun onComplete() {
                        isLoading.set(false)
                    }
                })
    }

    /**
     * Set warning text from recourse
     */
    private fun setWarningText(warning: Int){
        val context: Context = getApplication()
        warningText.set(context.getString(warning))
    }

    /**
     * Set warning text from string
     */
    private fun setEmptyWarningText(){
        warningText.set("")
    }

    /**
     * Create empty collection
     */
    private fun createEmptyCollection(): MutableSet<FullDish>{
        return mutableSetOf()
    }

    /**
     * Change favorite status in collection
     */
    private fun replaceFavoriteStatusInCollection(set: MutableSet<FullDish>?, dish: FullDish){
        set?.forEach{
            if(it == dish){
                it.isFavorite = dish.isFavorite
                return@forEach
            }
        }
    }
}