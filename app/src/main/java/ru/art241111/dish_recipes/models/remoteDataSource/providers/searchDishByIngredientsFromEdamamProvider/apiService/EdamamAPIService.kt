package ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromEdamamProvider.apiService


import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromEdamamProvider.dataModel.ResultEdamamApi

/**
 * API service for a request from a site edamam.com
 */
interface EdamamAPIService {
    /**
     * Method for selecting dishes by ingredients
     * @param app_id  - application id received on the site
     * @param app_key - key for the app received on the site
     * @param q - ingredients that we want to get from the API
     * @param from - the position from which we want to make a request (multiple of 10)
     * @return JSON object based on Result
     */
    @GET("/search")
    fun getData(@Query("app_id") appId: String?,
                     @Query("app_key") appKey: String,
                     @Query("q") ingredients: String,
                     @Query("from") startPosition: String)
            : Observable<ResultEdamamApi>

    /**
     * Companion object to create the EdamamAPIService
     */
    companion object Factory {
        fun create(): EdamamAPIService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.edamam.com")
                    .build()

            return retrofit.create(EdamamAPIService::class.java);
        }
    }
}
