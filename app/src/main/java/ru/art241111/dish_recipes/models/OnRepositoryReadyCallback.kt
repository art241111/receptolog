package ru.art241111.dish_recipes.models

import ru.art241111.dish_recipes.data.FullDish

interface OnRepositoryReadyCallback {
    fun onDataReady(data : ArrayList<FullDish>)
}