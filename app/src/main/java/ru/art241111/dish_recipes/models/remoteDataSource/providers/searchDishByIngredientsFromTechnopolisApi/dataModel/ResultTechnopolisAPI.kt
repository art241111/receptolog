package ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.dataModel

/**
 * Model of the result obtained from the query
 */
data class ResultTechnopolisAPI (val recipes : List<DishFromTechnopolisAPI>)