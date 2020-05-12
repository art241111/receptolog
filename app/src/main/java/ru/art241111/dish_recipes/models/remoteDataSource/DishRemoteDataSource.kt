package ru.art241111.dish_recipes.models.remoteDataSource

import io.reactivex.Observable
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsProvider.dataModel.Recipes
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsProvider.dataModel.Result
import ru.art241111.dish_recipes.models.remoteDataSource.providers.SearchRepositoryProvider


/**
 * Repository for getting remote data.
 * @author Artem Gerasimov.
 */
class DishRemoteDataSource {
    /**
     * Take data from remove repository.
     */
    fun getDishes(ingredients: ArrayList<String>, startPosition: String) : Observable<List<Recipes>> {
        return getDishesFromEdamamAPI(ingredients, startPosition).map{it.hits}
    }

    /**
     * Take dishes from edamam.com
     */
    private fun getDishesFromEdamamAPI(ingredients: ArrayList<String>, startPosition: String):Observable<Result> {
        val dishesRepository = SearchRepositoryProvider.provideSearchRepository()
        return dishesRepository.getDishes("8c1782dc",
                                          "e90b240c2c8118117d42cba520b31835",
                                                   ingredients, startPosition)
    }

}
