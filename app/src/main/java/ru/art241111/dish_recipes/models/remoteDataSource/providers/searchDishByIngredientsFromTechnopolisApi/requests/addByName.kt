package ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.requests

import io.reactivex.Observable
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.apiService.TechnopolisAPIService
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.dataModel.ResultTechnopolisAPI

object addByName {
    /**
     * Search by name
     * @param startPosition - the position from which we want to make a request (multiple of 10)
     * @param countOfIngredients - number of requested elements
     * @return Query result
     */
    fun addRequest(apiService: TechnopolisAPIService,
                   startPosition: String,
                   countOfIngredients: String,
                   name: List<String>): Observable<ResultTechnopolisAPI> {
        return apiService.getDishesWithoutParameters(
                startPosition = startPosition,
                countOfIngredients = countOfIngredients
        )
    }
}