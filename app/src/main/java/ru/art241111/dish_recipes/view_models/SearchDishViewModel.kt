package ru.art241111.dish_recipes.view_models

import android.app.Application
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

    private val dishRepository: DishRepository = DishRepository(NetManager(getApplication()))
    private val repositories = MutableLiveData<ArrayList<FullDish>>()

    private val isLoading = ObservableField(false)

    // TODO: Read about Disposable
    private val compositeDisposable = CompositeDisposable()

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
                        repositories.value = data
                    }

                    override fun onComplete() {
                        isLoading.set(false)
                    }
                })
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

}