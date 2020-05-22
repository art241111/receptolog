package ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi

import io.reactivex.Observable
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.apiService.TechnopolisAPIService
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.dataModel.ResultTechnopolisAPI
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.requests.AddAtLeastOneIngredient
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.requests.AddEmptyRequest
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.requests.AddOneToOneIngredient

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
                  countOfIngredients: String,
                  searchType: Int): Observable<ResultTechnopolisAPI> {
        val apiService = TechnopolisAPIService.create()

        return if(ingredients.isEmpty()){
            AddEmptyRequest.addRequest(apiService = apiService,
                            startPosition = startPosition,
                            countOfIngredients = countOfIngredients)

        } else{
            when(searchType){
                1->AddOneToOneIngredient.addIngredientsRequest(apiService = apiService,
                                                               ingredients = ingredients,
                                                               startPosition =  startPosition,
                                                               countOfIngredients = countOfIngredients)
                else -> AddAtLeastOneIngredient.addIngredientsRequest(apiService = apiService,
                                                                        ingredients = ingredients,
                                                                        startPosition =  startPosition,
                                                                        countOfIngredients = countOfIngredients)
            }

        }
    }




}