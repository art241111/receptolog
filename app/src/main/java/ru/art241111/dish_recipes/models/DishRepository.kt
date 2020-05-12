package ru.art241111.dish_recipes.models

import io.reactivex.Observable
import ru.art241111.dish_recipes.managers.NetManager
import ru.art241111.dish_recipes.models.localDataSource.DishLocalDataSource
import ru.art241111.dish_recipes.models.remoteDataSource.DishRemoteDataSource
import ru.art241111.dish_recipes.models.remoteDataSource.Recipes

/**
 * Repository for getting data.
 * @author Artem Geraimov.
 */
class DishRepository(val netManager: NetManager) {
    val localDataSource = DishLocalDataSource()
    val remoteDataSource = DishRemoteDataSource()
    /**
     * Get data from repositories.
     * If internet connect work, then data takes from internet,
     * else from local repository.
     * @return data from repositories.
     */
    fun getDishes(ingredients: ArrayList<String>, startPosition: String): Observable<List<Recipes>> {
        netManager.isConnectedToInternet?.let { it ->
            if (it) {
                return remoteDataSource.getDishes(ingredients,startPosition).flatMap {
                    return@flatMap localDataSource.saveDishes(it)
                            .toSingleDefault(it)
                            .toObservable()
                }
            }
        }
        return localDataSource.getRepositories()
    }
}