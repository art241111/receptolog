package ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromEdamamProvider.dataModel

/**
 * Model of the result obtained from the query
 */
data class ResultEdamamApi (val count: Int,
                            val hits: List<Recipes>)