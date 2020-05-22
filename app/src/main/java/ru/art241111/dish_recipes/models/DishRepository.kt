package ru.art241111.dish_recipes.models

import io.reactivex.Observable
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.managers.NetManager
import ru.art241111.dish_recipes.models.localDataSource.DishLocalDataSource
import ru.art241111.dish_recipes.models.localDataSource.favoriteDishes.protocols.getAllFavoriteDishes
import ru.art241111.dish_recipes.models.localDataSource.favoriteDishes.protocols.isDishFavorite
import ru.art241111.dish_recipes.models.localDataSource.favoriteDishes.protocols.removeFavoriteDishes
import ru.art241111.dish_recipes.models.localDataSource.favoriteDishes.protocols.saveFavoriteDishes
import ru.art241111.dish_recipes.models.remoteDataSource.DishRemoteDataSource
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromEdamamProvider.dataModel.Recipes

/**
 * Repository for getting data.
 * @author Artem Geraimov.
 */
class DishRepository(private val netManager: NetManager?): saveFavoriteDishes, getAllFavoriteDishes,
                                                           removeFavoriteDishes, isDishFavorite {
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
     * Save favorite dish to local repository.
     * @param dish - Dish, which the user added to favorite.
     * @return Information about whether the data was saved or not.
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

    override fun isDishFavorite(dish: FullDish): Boolean =
                localDataSource.isDishFavorite(dish)
}