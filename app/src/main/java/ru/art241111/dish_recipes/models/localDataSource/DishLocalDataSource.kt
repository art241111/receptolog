package ru.art241111.dish_recipes.models.localDataSource

import android.os.Handler
import ru.art241111.dish_recipes.data.Dish
import ru.art241111.dish_recipes.models.API.getDishes.getDishes

/**
 * Repository for getting local data
 */
class DishLocalDataSource {
    fun getRepositories(onRepositoryReadyCallback: OnDishLocalReadyCallback) {
        val arrayList = getDishes()
        Handler().postDelayed({ onRepositoryReadyCallback.onLocalDataReady(arrayList) }, 2000)
    }

    fun saveRepositories(arrayList: ArrayList<Dish>){
        //todo save repositories in DB
    }
}