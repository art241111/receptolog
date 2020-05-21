package ru.art241111.dish_recipes.models.remoteDataSource

import io.reactivex.Observable
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.models.DishRepository
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromEdamamProvider.dataModel.Recipes
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromEdamamProvider.dataModel.ResultEdamamApi
import ru.art241111.dish_recipes.models.remoteDataSource.providers.SearchRepositoryProvider
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromEdamamProvider.dataModel.DishModel
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.dataModel.ResultTechnopolisAPI


/**
 * Repository for getting remote data.
 * @author Artem Gerasimov.
 */
class DishRemoteDataSource {
    /**
     * Take data from  remove repository.
     */
    fun getDishes(ingredients: ArrayList<String>, startPosition: String) : Observable<List<FullDish>> {
        val recipes:  Observable<List<Recipes>> = getDishesFromEdamamAPI(ingredients, startPosition).map{it.hits}
        return recipes.map{recipe ->
            return@map recipe.map{
                dish -> migrateDishModelToFullDish(dish.recipe)}
        }
    }

    /**
     * Convert Dish model to FullDish model.
     * Set title, imageUrl.
     * @param recipes - recipe model to convert.
     * @return convertible recipe.
     */
    private fun migrateDishModelToFullDish(dishModel: DishModel):FullDish {
            val fullDish = FullDish()

            fullDish.urlImageRecipe = dishModel.image
            fullDish.nameDish = dishModel.label
            fullDish.ingredients = dishModel.ingredientLines
            fullDish.setDescriptionDishFromArray(dishModel.healthLabels)

            fullDish.isFavorite = DishRepository(null).isDishFavorite(fullDish)
            return fullDish
    }

    /**
     * Take dishes from edamam.com
     */
    private fun getDishesFromEdamamAPI(ingredients: ArrayList<String>, startPosition: String):Observable<ResultEdamamApi> {
        val dishesRepository = SearchRepositoryProvider.provideSearchRepositoryFromEdamam()
        return dishesRepository.getDishes("8c1782dc",
                                          "e90b240c2c8118117d42cba520b31835",
                                                   ingredients, startPosition)
    }

    /**
     * Take dishes from technopolis API
     */
    private fun getDishesFromTechnopolisAPI(ingredients: ArrayList<String>,
                                            startPosition: String,
                                            countOfIngredients: String):Observable<ResultTechnopolisAPI> {
        val dishesRepository = SearchRepositoryProvider.provideSearchRepositoryFromTechnopolis()
        val result = dishesRepository.getDishes( ingredients = ingredients,
                                                 startPosition = startPosition,
                                                 countOfIngredients = countOfIngredients)

        return result
    }



}
