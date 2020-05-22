package ru.art241111.dish_recipes.models.localDataSource

import io.reactivex.Observable
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.models.localDataSource.favoriteDishes.FavoriteDishes
import ru.art241111.dish_recipes.models.localDataSource.favoriteDishes.protocols.getAllFavoriteDishes
import ru.art241111.dish_recipes.models.localDataSource.favoriteDishes.protocols.isDishFavorite
import ru.art241111.dish_recipes.models.localDataSource.favoriteDishes.protocols.removeFavoriteDishes
import ru.art241111.dish_recipes.models.localDataSource.favoriteDishes.protocols.saveFavoriteDishes
//import ru.art241111.dish_recipes.models.API.getDishes.getDishes
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromEdamamProvider.dataModel.Recipes

/**
 * Repository for getting and saving local data.
 * @author Artem Geraimov.
 */
class DishLocalDataSource: saveFavoriteDishes, getAllFavoriteDishes,
                           removeFavoriteDishes, isDishFavorite {
    /**
     * Take data from local repository.
     * @return data from local repository.
     */
    fun getRepositories(): Observable<List<FullDish>> {
        return Observable.fromArray(listOf())
    }

    /**
     * Save favorite dish to local repository.
     * @param dish - Dish, which the user added to favorite.
     * @return Information about whether the data was saved or not.
     */
    override fun addFavoriteDishes(dish: FullDish) {
        FavoriteDishes().addFavoriteDishes(dish)
    }

    /**
     * Remove dish to favorite list
     * @param dish - the dish that you want to remove
     */
    override fun removeFavoriteDishes(dish: FullDish) {
        FavoriteDishes().removeFavoriteDishes(dish)
    }

    /**
     * Get all favorite dishes from local repository.
     * @return Observable array with favorite dishes.
     */
    override fun getAllFavoriteDishes(): List<FullDish> =
                FavoriteDishes().getAllFavoriteDishes()

    override fun isDishFavorite(dish: FullDish): Boolean =
                FavoriteDishes().isDishFavorite(dish)
}