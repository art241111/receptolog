package ru.art241111.dish_recipes.models.remoteDataSource

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.art241111.dish_recipes.data.FullDish
import java.util.concurrent.TimeUnit


/**
 * Repository for getting remote data.
 * @author Artem Gerasimov.
 */
class DishRemoteDataSource {
    /**
     * Take data from remove repository.
     */
    fun getDishes(ingredients: ArrayList<String>) : Observable<List<Recipes>> {
        val arrayList = ru.art241111.dish_recipes.models.API.getDishes.getDishes()
        return getDishesFromEdamamAPI(ingredients).map{it.hits}
    }

    private fun getDishesFromEdamamAPI(ingredients: ArrayList<String>):Observable<Result> {
        val dishesRepository = SearchRepositoryProvider.provideSearchRepository()
        return dishesRepository.getDishes("8c1782dc",
                                          "e90b240c2c8118117d42cba520b31835",
                                                   ingredients)
    }

}
