package ru.art241111.dish_recipes.view_models

import android.app.Application
import android.text.Editable
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
import ru.art241111.kotlinmvvm.extensionFunctions.plusAssign


class SearchDishViewModel(application: Application)
                             : AndroidViewModel(application) {

    // Data repository
    private val dishRepository: DishRepository = DishRepository(NetManager(getApplication()))

    // Array of dishes
    val dishes = MutableLiveData<ArrayList<FullDish>>()

    // Array of ingredients
    val ingredientsArray = ArrayList<String>()

    // Check data is loading or not
    val isLoading = ObservableField(false)

    // TODO: Read about Disposable
    private val compositeDisposable = CompositeDisposable()

    /**
     * load dishes from repositories
     */
    fun loadDishes() {
        isLoading.set(true)
        compositeDisposable += dishRepository
                .getRepositories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ArrayList<FullDish>>() {
                    override fun onError(e: Throwable) {
                        //if some error happens in our data layer our app will not crash, we will
                        // get error here
                    }

                    override fun onNext(data: ArrayList<FullDish>) {
                        dishes.value = data
                    }

                    override fun onComplete() {
                        isLoading.set(false)
                    }
                })
    }

    // Reply
    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    fun addIngredient(ingredient: String){
        ingredientsArray.add(ingredient)
    }

    fun deleteIngredient(ingredient: String){
        ingredientsArray.remove(ingredient)
    }
}