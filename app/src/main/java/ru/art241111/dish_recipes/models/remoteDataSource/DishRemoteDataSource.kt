package ru.art241111.dish_recipes.models.remoteDataSource

import android.os.Handler
import ru.art241111.dish_recipes.models.API.getDishes.getDishes

/**
 * Repository for getting remote data
 */

class DishRemoteDataSource {
    fun getRepositories(onRepositoryReadyCallback: OnDishRemoteReadyCallback) {
        val arrayList = getDishes()
        Handler().postDelayed({ onRepositoryReadyCallback.onRemoteDataReady(arrayList) }, 2000)
    }
}