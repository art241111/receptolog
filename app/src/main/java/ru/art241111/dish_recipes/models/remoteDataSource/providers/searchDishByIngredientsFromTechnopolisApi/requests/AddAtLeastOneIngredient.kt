package ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.requests

import io.reactivex.Observable
import ru.art241111.dish_recipes.extensionFunctions.toStringWithSpacesAndCommas
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.apiService.TechnopolisAPIService
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.dataModel.ResultTechnopolisAPI

object AddAtLeastOneIngredient {
    /**
     * Query with ingredients filter
     * @param ingredients - ingredients that we want to get from the API
     * @param startPosition - the position from which we want to make a request
     * @param countOfIngredients - number of requested elements
     * @return Query result
     */
    fun addIngredientsRequest(apiService: TechnopolisAPIService,
                              startPosition: String,
                              countOfIngredients: String,
                              ingredients: List<String>): Observable<ResultTechnopolisAPI> {
        val queryIngredients = ingredients.toStringWithSpacesAndCommas()

        return apiService.getDataWhenLeastOneIngredientIsPresent(
                ingredients = queryIngredients,
                startPosition = startPosition,
                countOfIngredients = countOfIngredients)
    }
}