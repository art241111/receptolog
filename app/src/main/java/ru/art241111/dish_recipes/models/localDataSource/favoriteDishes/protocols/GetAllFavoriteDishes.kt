package ru.art241111.dish_recipes.models.localDataSource.favoriteDishes.protocols

import ru.art241111.dish_recipes.data.FullDish

interface GetAllFavoriteDishes {
    fun getAllFavoriteDishes(): List<FullDish>
}