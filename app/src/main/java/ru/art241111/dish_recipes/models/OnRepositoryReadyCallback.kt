package ru.art241111.dish_recipes.models

import ru.art241111.dish_recipes.data.Dish

interface OnRepositoryReadyCallback {
    fun onDataReady(data : ArrayList<Dish>)
}