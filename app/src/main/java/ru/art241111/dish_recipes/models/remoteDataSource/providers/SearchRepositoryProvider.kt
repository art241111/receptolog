package ru.art241111.dish_recipes.models.remoteDataSource.providers

import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromEdamamProvider.SearchDishes
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.SearchDishesFromTechnopolis

/**
 * Object that stores implementations providers.
 * @author Artem Gerasimov.
 */
object SearchRepositoryProvider {
    fun provideSearchRepositoryFromEdamam(): SearchDishes {
        return SearchDishes()
    }

    fun provideAtLeastOneSearchRepositoryFromTechnopolis(): SearchDishesFromTechnopolis {
        return SearchDishesFromTechnopolis()
    }


}