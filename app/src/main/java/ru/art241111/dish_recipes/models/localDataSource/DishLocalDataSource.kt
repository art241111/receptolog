package ru.art241111.dish_recipes.models.localDataSource

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
//import ru.art241111.dish_recipes.models.API.getDishes.getDishes
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsProvider.dataModel.Recipes
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
    fun getRepositories(): Observable<List<Recipes>> {
        return Observable.empty()
    }

    /**
     * Save date from remote repository to local repository.
     * @param arrayList - array to save.
     * @return Information about whether the data was saved or not.
     */
    fun saveDishes(arrayList: List<Recipes>): Completable {
        return Single.just(1).toCompletable()
    }
}