package ru.art241111.dish_recipes.models.localDataSource

import android.os.Handler
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.models.API.getDishes.getDishes
import java.util.concurrent.TimeUnit

/**
 * Repository for getting and saving local data.
 * @author Artem Geraimov.
 */
class DishLocalDataSource {
    /**
     * Take data from local repository.
     * @return data from local repository.
     */
    fun getRepositories(): Observable<ArrayList<FullDish>> {
        val arrayList = getDishes()
        return Observable.just(arrayList).delay(2, TimeUnit.SECONDS)
    }

    /**
     * Save date from remote repository to local repository.
     * @param arrayList - array to save.
     * @return Information about whether the data was saved or not.
     */
    fun saveRepositories(arrayList: ArrayList<FullDish>): Completable {
        return Single.just(1).delay(1, TimeUnit.SECONDS).toCompletable()
    }
}