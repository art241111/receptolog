package ru.art241111.dish_recipes.view_models

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.managers.NetManager
import ru.art241111.dish_recipes.models.DishRepository
import ru.art241111.dish_recipes.models.remoteDataSource.DishModel
import ru.art241111.dish_recipes.models.remoteDataSource.Recipes
import ru.art241111.kotlinmvvm.extensionFunctions.plusAssign

/**
 * ViewModel to work with data and searchDishActivity
 * @author Artem Geraimov.
 */
class SearchDishViewModel(application: Application)
                             : AndroidViewModel(application) {

    // Data repository.
    private val dishRepository: DishRepository = DishRepository(NetManager(getApplication()))

    // Array of dishes.
    val dishes = MutableLiveData<ArrayList<FullDish>>()

    // Array of ingredients.
    val ingredients = ArrayList<String>()

    // Check data is loading or not.
    val isLoading = ObservableField(false)

    // TODO: Read about Disposable
    private val compositeDisposable = CompositeDisposable()

    // is application create firs
    var isApplicationCreateFirst = true

    /**
     * load dishes from repositories.
     */
    fun loadDishes() {
        isLoading.set(true)

        // Get information from repository.
        compositeDisposable += dishRepository
                .getDishes(ingredients)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<Recipes>>() {
                    override fun onError(e: Throwable) {
                        Log.e("SearchDishViewModel.kt",
                                "Error with reading data + ${e.message}")
                    }

                    override fun onNext(data: List<Recipes>) {
                        val dishesArrayList = ArrayList<FullDish>()
                        data.map{dishesArrayList.add(RecipeToDish(it)) }
                        dishes.value = dishesArrayList
                    }

                    override fun onComplete() {
                        isLoading.set(false)
                    }
                })
    }

    /**
     * Convert recipe model to FullDish model.
     * Set title, imageUrl.
     * @param recipes - recipe model to convert.
     * @return convertible recipe.
     */
    private fun RecipeToDish(recipes: Recipes): FullDish {
        val dishModel: DishModel = recipes.recipe
        val fullDish = FullDish()

        fullDish.urlImageRecipe = dishModel.image
        fullDish.nameDish = dishModel.label
        fullDish.ingredients = dishModel.ingredientLines
        return fullDish
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
}