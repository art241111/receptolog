package ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsProvider.dataModel

/**
 * Model of dish
 */
data class DishModel(
        val image: String,
        val ingredientLines: List<String>,
        val label: String,
        val uri: String,
        val healthLabels: List<String>
)