package ru.art241111.dish_recipes.view_models.protocols

import ru.art241111.dish_recipes.R

interface LoadDishesFromRemoteRepository {
    /**
     * Load dishes when screen create.
     */
    fun loadDishesWhenScreenCreate(){}

    /**
     * Load new data, when data on screen end
     */
    fun loadDishesWhenDataEnd(){}

    /**
     * Load new data
     */
    fun loadNewDishes(){}
}