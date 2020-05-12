package ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsProvider.dataModel

/**
 * Model of the result obtained from the query
 */
data class Result (val count: Int,
                   val hits: List<Recipes>)