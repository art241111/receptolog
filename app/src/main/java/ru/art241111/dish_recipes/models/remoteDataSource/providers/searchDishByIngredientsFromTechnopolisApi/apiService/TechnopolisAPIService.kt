package ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.apiService

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromEdamamProvider.dataModel.ResultEdamamApi
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.dataModel.ResultTechnopolisAPI

interface TechnopolisAPIService {
    /**
     * Method for selecting dishes without parameters
     * @param startPosition (from) - the position from which we want to make a request (multiple of 10)
     * @param countOfIngredients (count) - count
     * @return JSON object based on Result
     */
    @GET("/recipes-list")
    fun getDishesWithoutParameters(
            @Query("from") startPosition: String,
            @Query("count") countOfIngredients: String)
            : Observable<ResultTechnopolisAPI>

    /**
     * Method for selecting dishes by ingredients
     * @param ingredients (ing) - ingredients that we want to get from the API
     * @param startPosition (from) - the position from which we want to make a request (multiple of 10)
     * @param countOfIngredients (count) - count
     * @return JSON object based on Result
     */
    @GET("/recipes/with-these-ingredients?")
    fun getDataWhenLeastOneIngredientIsPresent(
                @Query("ing") ingredients: String,
                @Query("from") startPosition: String,
                @Query("count") countOfIngredients: String)
            : Observable<ResultTechnopolisAPI>


    /**
    * Method for selecting dishes without parameters
    * @param startPosition (from) - the position from which we want to make a request (multiple of 10)
    * @param countOfIngredients (count) - count
    * @return JSON object based on Result
    */
    @GET("/recipes/with-all-these-ingredients?")
    fun getAllIngredientsMustBePresent(
            @Query("ing") ingredients: String,
            @Query("from") startPosition: String,
            @Query("count") countOfIngredients: String)
            : Observable<ResultTechnopolisAPI>

    /**
     * Method for selecting dishes by name
     * @param name - dishes name
     * @param startPosition (from) - the position from which we want to make a request (multiple of 10)
     * @param countOfIngredients (count) - count
     * @return JSON object based on Result
     */
    @GET("/recipes-by-name?")
    fun getDishesByName(
            @Query("name") name: String,
            @Query("from") startPosition: String,
            @Query("count") countOfIngredients: String)
            : Observable<ResultTechnopolisAPI>

    /**
     * Companion object to create the TechnopolisAPIService
     */
    companion object Factory {
        fun create(): TechnopolisAPIService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://recipetolog.herokuapp.com")
                    .build()

            return retrofit.create(TechnopolisAPIService::class.java)
        }
    }
}