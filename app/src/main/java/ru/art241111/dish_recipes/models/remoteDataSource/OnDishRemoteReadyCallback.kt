package ru.art241111.dish_recipes.models.remoteDataSource

import ru.art241111.dish_recipes.data.Dish

interface OnDishRemoteReadyCallback {
    fun onRemoteDataReady(data: ArrayList<Dish>)
}