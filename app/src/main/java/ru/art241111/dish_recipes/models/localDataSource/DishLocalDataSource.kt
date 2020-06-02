package ru.art241111.dish_recipes.models.localDataSource

import io.reactivex.Observable
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.models.localDataSource.favoriteDishes.FavoriteDishes
import ru.art241111.dish_recipes.models.localDataSource.favoriteDishes.protocols.*

//import ru.art241111.dish_recipes.models.API.getDishes.getDishes

/**
 * Repository for getting and saving local data.
 * @author Artem Geraimov.
 */
class DishLocalDataSource: SaveFavoriteDishes, GetAllFavoriteDishes,
                           RemoveFavoriteDishes, IsDishFavorite {
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