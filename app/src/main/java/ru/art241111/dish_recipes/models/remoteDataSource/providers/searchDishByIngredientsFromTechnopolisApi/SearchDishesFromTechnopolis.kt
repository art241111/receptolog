package ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi

import io.reactivex.Observable
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromEdamamProvider.dataModel.ResultEdamamApi
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.apiService.TechnopolisAPIService
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.dataModel.ResultTechnopolisAPI

/**
 * Provider for requesting dishes by ingredients to the service
 */
class SearchDishesFromTechnopolis{
    /**
     * Provider for requesting dishes by ingredients to the service
     * @param appId  - application id received on the site
     * @param appKey - key for the app received on the site
     * @param ingredients - ingredients that we want to get from the API
     * @param startPosition - the position from which we want to make a request (multiple of 10)
     * @return Query result
     */
    fun getDishes(ingredients: List<String>,
                  startPosition: String,
                  countOfIngredients: String): Observable<ResultTechnopolisAPI> {
        val apiService = TechnopolisAPIService.create()


        return if(ingredients.isEmpty()){
            addEmptyRequest(apiService = apiService,
                            startPosition = startPosition,
                            countOfIngredients = countOfIngredients)
        } else{
            addIngredientsRequest(apiService = apiService,
                                  ingredients = ingredients,
                                  startPosition =  startPosition,
                                  countOfIngredients = countOfIngredients)
        }
    }

    /**
     * If user don't write ingredients to filter
     * @param appId  - application id received on the site
     * @param appKey - key for the app received on the site
     * @param startPosition - the position from which we want to make a request (multiple of 10)
     * @return Query result
     */
    private fun addEmptyRequest(apiService: TechnopolisAPIService,
                                 startPosition: String,
                                countOfIngredients: String): Observable<ResultTechnopolisAPI> {
        return apiService.getDishesWithoutParameters(
                startPosition = startPosition,
                countOfIngredients = countOfIngredients
        )
    }

    /**
     * Query with ingredients filter
     * @param appId  - application id received on the site
     * @param appKey - key for the app received on the site
     * @param ingredients - ingredients that we want to get from the API
     * @param startPosition - the position from which we want to make a request (multiple of 10)
     * @return Query result
     */
    private fun addIngredientsRequest(apiService: TechnopolisAPIService,
                                      startPosition: String,
                                      countOfIngredients: String,
                                      ingredients: List<String>): Observable<ResultTechnopolisAPI> {
        var queryIngredients = ""
        for(ingredient in ingredients){
            queryIngredients += "$ingredient,"
        }

        return apiService.getDataWhenLeastOneIngredientIsPresent(
                ingredients = queryIngredients,
                startPosition = startPosition,
                countOfIngredients = countOfIngredients)
    }
}