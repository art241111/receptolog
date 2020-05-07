package ru.art241111.dish_recipes.models.remoteDataSource

import ru.art241111.dish_recipes.data.FullDish

interface OnDishRemoteReadyCallback {
    fun onRemoteDataReady(data: ArrayList<FullDish>)
}