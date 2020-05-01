package ru.art241111.dish_recipes.models.localDataSource

import ru.art241111.dish_recipes.data.Dish

interface OnDishLocalReadyCallback {
    fun onLocalDataReady(data: ArrayList<Dish>)
}