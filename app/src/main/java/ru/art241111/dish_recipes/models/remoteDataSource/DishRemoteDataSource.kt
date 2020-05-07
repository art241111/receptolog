package ru.art241111.dish_recipes.models.remoteDataSource

import android.os.Handler
import io.reactivex.Observable
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.models.API.getDishes.getDishes
import java.util.concurrent.TimeUnit

/**
 * Repository for getting remote data
 */

class DishRemoteDataSource {
    fun getRepositories() : Observable<ArrayList<FullDish>> {
        val arrayList = getDishes()
        return Observable.just(arrayList).delay(2, TimeUnit.SECONDS)
    }
}