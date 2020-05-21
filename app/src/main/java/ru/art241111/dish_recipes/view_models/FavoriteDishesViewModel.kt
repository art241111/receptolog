package ru.art241111.dish_recipes.view_models

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.managers.NetManager
import ru.art241111.dish_recipes.models.DishRepository

class FavoriteDishesViewModel (application: Application): AndroidViewModel(application) {
    // Data repository.
    private val dishRepository: DishRepository = DishRepository(NetManager(getApplication()))

    // Array of dishes.
    val dishes = MutableLiveData<List<FullDish>>()

    /**
     * load dishes from repositories.
     */
    fun loadFavoriteDishes() {
        dishes.value = dishRepository.getAllFavoriteDishes()
    }

}