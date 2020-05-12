package ru.art241111.dish_recipes.models.remoteDataSource


import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface EdamamAPIService {
    @GET("/search")
    open fun getData(@Query("app_id") appId: String?,
                     @Query("app_key") appKey: String,
                     @Query("q") ingredients: String,
                     @Query("from") startPosition: String)
            : Observable<Result>

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
