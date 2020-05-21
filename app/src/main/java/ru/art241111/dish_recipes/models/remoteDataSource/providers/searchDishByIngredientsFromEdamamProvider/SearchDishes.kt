package ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromEdamamProvider

import io.reactivex.Observable
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromEdamamProvider.apiService.EdamamAPIService
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromEdamamProvider.dataModel.ResultEdamamApi

/**
 * Provider for requesting dishes by ingredients to the service
 */
class SearchDishes(){
    /**
     * Provider for requesting dishes by ingredients to the service
     * @param appId  - application id received on the site
     * @param appKey - key for the app received on the site
     * @param ingredients - ingredients that we want to get from the API
     * @param startPosition - the position from which we want to make a request (multiple of 10)
     * @return Query result
     */
    fun getDishes(appId:String, appKey: String,
                  ingredients: List<String>,
                  startPosition: String): Observable<ResultEdamamApi> {
        val apiService = EdamamAPIService.create()

        return if(ingredients.isEmpty()){
            addEmptyRequest(apiService, appId, appKey, startPosition)
        } else{
            addIngredientsRequest(apiService, appId, appKey, ingredients, startPosition)
        }
    }

    /**
     * If user don't write ingredients to filter
     * @param appId  - application id received on the site
     * @param appKey - key for the app received on the site
     * @param startPosition - the position from which we want to make a request (multiple of 10)
     * @return Query result
     */
    private fun addEmptyRequest(apiService: EdamamAPIService,
                                appId:String, appKey: String,
                                startPosition: String): Observable<ResultEdamamApi> {
        return apiService.getData(appId = appId,
                appKey = appKey,
                ingredients = "null",
                startPosition = startPosition)
    }

    /**
     * Query with ingredients filter
     * @param appId  - application id received on the site
     * @param appKey - key for the app received on the site
     * @param ingredients - ingredients that we want to get from the API
     * @param startPosition - the position from which we want to make a request (multiple of 10)
     * @return Query result
     */
    private fun addIngredientsRequest(apiService: EdamamAPIService,
                                      appId:String, appKey: String,
                                      ingredients: List<String>,
                                      startPosition: String): Observable<ResultEdamamApi> {
        var queryIngredients = ""
        for(ingredient in ingredients){
            queryIngredients += "$ingredient,"
        }

        return apiService.getData(appId = appId,
                appKey = appKey,
                ingredients = queryIngredients,
                startPosition = startPosition)
    }
}