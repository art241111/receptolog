package ru.art241111.dish_recipes.models

import io.reactivex.Observable
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.managers.NetManager
import ru.art241111.dish_recipes.models.localDataSource.DishLocalDataSource
import ru.art241111.dish_recipes.models.remoteDataSource.DishRemoteDataSource

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
    fun getRepositories(): Observable<ArrayList<FullDish>> {
        netManager.isConnectedToInternet?.let { it ->
            if (it) {
                return remoteDataSource.getRepositories().flatMap {
                    return@flatMap localDataSource.saveRepositories(it)
                            .toSingleDefault(it)
                            .toObservable()
                }
            }
        }
        return localDataSource.getRepositories()
    }
}