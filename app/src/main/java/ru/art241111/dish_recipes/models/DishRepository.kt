package ru.art241111.dish_recipes.models

import io.reactivex.Observable
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.managers.NetManager
import ru.art241111.dish_recipes.models.localDataSource.DishLocalDataSource
import ru.art241111.dish_recipes.models.localDataSource.favoriteDishes.protocols.*
import ru.art241111.dish_recipes.models.remoteDataSource.DishRemoteDataSource

/**
 * Repository for getting data.
 * @author Artem Geraimov.
 */
class DishRepository(private val netManager: NetManager?): SaveFavoriteDishes, GetAllFavoriteDishes,
                                                           RemoveFavoriteDishes, IsDishFavorite,
                                                           ChangeFavoriteStatus {
    private val localDataSource = DishLocalDataSource()
    private val remoteDataSource = DishRemoteDataSource()

    /**
     * Get data from repositories.
     * If internet connect work, then data takes from internet,
     * else from local repository.
     * @return data from repositories.
     */
    fun getDishes(ingredients: ArrayList<String>, startPosition: String, searchType: Int)
                                                                    : Observable<List<FullDish>> {
        netManager?.isConnectedToInternet?.let { it ->
            if (it) {
                return remoteDataSource.getDishes(ingredients,startPosition,searchType)
            }
        }
        return localDataSource.getRepositories()
    }

    /**
     * Change favorite status of dishes.
     * @param dish - The dish that needs to change its status.
     */
    override fun changeFavoriteStatus(dish: FullDish) {
        dish.isFavorite = !dish.isFavorite
        if(dish.isFavorite){
            addFavoriteDishes(dish)
        } else {
            removeFavoriteDishes(dish)
        }
    }

    /**
     * Save favorite dish to local repository.
     * @param dish - Dish, which the user added to favorite.
     */
    override fun addFavoriteDishes(dish: FullDish) {
        localDataSource.addFavoriteDishes(dish)
    }

    /**
     * Remove dish to favorite list
     * @param dish - the dish that you want to remove
     */
    override fun removeFavoriteDishes(dish: FullDish) {
        localDataSource.removeFavoriteDishes(dish)
    }

    /**
     * Get all favorite dishes from local repository.
     * @return Observable array with favorite dishes.
     */
    override fun getAllFavoriteDishes(): List<FullDish> =
               localDataSource.getAllFavoriteDishes()

    /**
     * Return dish favorite status.
     * @return favorite status .
     */
    override fun isDishFavorite(dish: FullDish): Boolean =
                localDataSource.isDishFavorite(dish)
}