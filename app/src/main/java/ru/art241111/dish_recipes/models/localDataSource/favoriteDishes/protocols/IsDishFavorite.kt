package ru.art241111.dish_recipes.models.localDataSource.favoriteDishes.protocols

import ru.art241111.dish_recipes.data.FullDish

interface IsDishFavorite {
    fun isDishFavorite(dish: FullDish): Boolean
}