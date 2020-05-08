package ru.art241111.dish_recipes.models.remoteDataSource


data class DishModel(
    val image: String,
    val ingredientLines: List<String>,
    val label: String,
    val uri: String
)

data class Recipes(
        val recipe: DishModel
)
data class Result (val count: Int,
                   val hits: List<Recipes>)