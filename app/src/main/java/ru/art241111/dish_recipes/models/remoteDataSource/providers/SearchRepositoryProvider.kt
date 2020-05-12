package ru.art241111.dish_recipes.models.remoteDataSource.providers

import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsProvider.SearchDishes

/**
 * Object that stores implementations providers.
 * @author Artem Gerasimov.
 */
object SearchRepositoryProvider {
    fun provideSearchRepository(): SearchDishes {
        return SearchDishes()
    }
}