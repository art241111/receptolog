package ru.art241111.dish_recipes.view_models.protocols

import ru.art241111.dish_recipes.data.FullDish

interface UpdateFavorite {
    fun updateFavoriteAtOneDish(dish: FullDish)
}