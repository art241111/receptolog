package ru.art241111.dish_recipes.models.remoteDataSource

import io.reactivex.Observable
import java.text.FieldPosition


class SearchDishes(){
    fun getDishes(app_id:String, appKey: String,
                  ingredients: List<String>,
                  startPosition: String): Observable<Result> {
        val apiService = EdamamAPIService.create()
        if (ingredients.isEmpty()){
            return apiService.getData(appId = app_id,
                                      appKey = appKey,
                                      ingredients = "all",
                                      startPosition = startPosition)
        } else{
            var queryIngredients = ""
            for(ingredient in ingredients){
                queryIngredients += "$ingredient,"
            }

            return apiService.getData(appId = app_id,
                                      appKey = appKey,
                                      ingredients = queryIngredients,
                                      startPosition = startPosition)
        }

    }
}