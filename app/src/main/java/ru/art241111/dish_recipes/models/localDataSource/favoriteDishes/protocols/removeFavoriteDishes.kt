package ru.art241111.dish_recipes.models.localDataSource.favoriteDishes.protocols

import ru.art241111.dish_recipes.data.FullDish

interface removeFavoriteDishes {
    fun removeFavoriteDishes(dish: FullDish)
}