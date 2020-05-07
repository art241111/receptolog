package ru.art241111.dish_recipes.view_models

import android.app.Application
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.managers.NetManager
import ru.art241111.dish_recipes.models.DishRepository
import ru.art241111.dish_recipes.models.OnRepositoryReadyCallback


class SearchDishViewModel(application: Application)
                             : AndroidViewModel(application) {

    var dishRepository: DishRepository = DishRepository(NetManager(getApplication()))
    var repositories = MutableLiveData<ArrayList<FullDish>>()

    val isLoading = ObservableField(false)

    fun loadDishes() {
        dishRepository.getRepositories(object : OnRepositoryReadyCallback {
            override fun onDataReady(data: ArrayList<FullDish>) {
                isLoading.set(false)
                repositories.value = data
            }
        })
    }

}