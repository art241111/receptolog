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
import ru.art241111.dish_recipes.view_models.protocols.UpdateFavorite
import ru.art241111.kotlinmvvm.extensionFunctions.plusAssign

/**
 * ViewModel to work with data and searchDishActivity
 * @author Artem Geraimov.
 */
class SearchDishViewModel(application: Application)
                             : AndroidViewModel(application), UpdateFavorite {
    // Data repository.
    private val netManager = NetManager(getApplication())
    private val dishRepository: DishRepository = DishRepository(netManager)

    // Array of dishes.
    val dishes = MutableLiveData<ArrayList<FullDish>>()
    private var dishesArrayList = ArrayList<FullDish>()

    // Array of ingredients.
    val ingredients = ArrayList<String>()

    // Check data is loading or not.
    val isLoading = ObservableField(false)
    val warningText = ObservableField("")
    var areThereAnyOtherRecipes = true

    // TODO: Read about Disposable
    private val compositeDisposable = CompositeDisposable()

    // start page to load data
    private var startPosition = 0

    // spinner position
    var spinnerPosition = 0

    /**
     * Load new data, when data on screen end
     */
    fun loadDishesWhenDataEnd(){
        if(netManager.isConnectedToInternet){
            if(areThereAnyOtherRecipes){
                startPosition += 11

                loadDishes()
                setWarningText("")
            }
        } else{
            setWarningText(R.string.no_internet_connection)
        }
    }

    fun loadDishesWhenScreenCreate(){
        if(dishesArrayList.isEmpty()){
            loadNewDishes()
        } else{
            dishes.value = dishesArrayList
        }
    }
    /**
     * Load new data, when user enter new ingredient
     */
    fun loadNewDishes(){
        if(netManager.isConnectedToInternet){
            areThereAnyOtherRecipes = true
            startPosition = 0
            isLoading.set(true)
            this.dishesArrayList = ArrayList()

            loadDishes()
            setWarningText("")
        } else{
            setWarningText(R.string.no_internet_connection)
            dishes.value = arrayListOf()
        }
    }

    private fun setWarningText(warning: Int){
        val context: Context = getApplication()
        warningText.set(context.getString(warning))
    }

    private fun setWarningText(warning: String){
        warningText.set(warning)
    }
    /**
     * load dishes from repositories.
     */
    private fun loadDishes() {
        compositeDisposable += dishRepository
                .getDishes(ingredients, startPosition.toString(), spinnerPosition)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<FullDish>>() {
                    override fun onError(e: Throwable) {
                        Log.e("SearchDishViewModel.kt",
                                "Error with reading data + ${e.message}")
                    }

                    override fun onNext(data: List<FullDish>) {
                        if(data.isEmpty()) {
                            setWarningText(R.string.no_recipes_with_this_ingredient_were_found)
                            dishes.value= arrayListOf()
                        }

//                        dishesArrayList = data as ArrayList<FullDish>
                         data.map{
                                dishesArrayList.add(it)
                            }
                        dishes.value = dishesArrayList

                        if(data.size < 10) areThereAnyOtherRecipes = false
                    }

                    override fun onComplete() {
                        isLoading.set(false)
                    }
                })
    }

    override fun updateFavoriteAtOneDish(dish: FullDish) {
        dishes.value?.forEach{
            if(it == dish){
                it.isFavorite = dish.isFavorite
            }
        }
        dishesArrayList.forEach(){
            if(it == dish){
                it.isFavorite = dish.isFavorite
            }
        }
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
    fun addIngredient(ingredient: String) {
        ingredients.add(ingredient)
    }

    /**
     * Remove ingredient to ingredients array.
     */
    fun deleteIngredient(ingredient: String) {
        ingredients.remove(ingredient)
    }

    /**
     * load dishes from repositories.
     */
    fun loadFavoriteDishes() {
        dishes.value = dishRepository.getAllFavoriteDishes() as ArrayList<FullDish>
    }
}