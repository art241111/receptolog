package ru.art241111.dish_recipes.models.remoteDataSource

object SearchRepositoryProvider {
    fun provideSearchRepository(): SearchDishes {
        return SearchDishes()
    }
}