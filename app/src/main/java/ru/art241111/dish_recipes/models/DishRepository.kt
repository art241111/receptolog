package ru.art241111.dish_recipes.models

import io.reactivex.Observable
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.managers.NetManager
import ru.art241111.dish_recipes.models.localDataSource.DishLocalDataSource
import ru.art241111.dish_recipes.models.remoteDataSource.DishRemoteDataSource

/**
 * Repository for getting data
 */
class DishRepository(val netManager: NetManager) {
    val localDataSource = DishLocalDataSource()
    val remoteDataSource = DishRemoteDataSource()

    fun getRepositories(): Observable<ArrayList<FullDish>> {
        netManager.isConnectedToInternet?.let {
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