package ru.art241111.dish_recipes.models.remoteDataSource

import io.reactivex.Observable
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.extensionFunctions.toStringWithEnter
import ru.art241111.dish_recipes.models.DishRepository
import ru.art241111.dish_recipes.models.remoteDataSource.providers.SearchRepositoryProvider
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromEdamamProvider.dataModel.DishModel
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.dataModel.DishFromTechnopolisAPI
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.dataModel.Ingredients

/**
 * Repository for getting remote data.
 * @author Artem Gerasimov.
 */
class DishRemoteDataSource {
    /**
     * Take data from  remove repository.
     */
    fun getDishes(ingredients: ArrayList<String>, startPosition: String, searchType: Int)
                                                        : Observable<List<FullDish>> {

        return getDishesFromTechnopolisAPI(ingredients, startPosition,"10",searchType)
    }
    /**
     * Take dishes from technopolis API
     */
    private fun getDishesFromTechnopolisAPI(ingredients: ArrayList<String>,
                                            startPosition: String,
                                            countOfIngredients: String,
                                            searchType: Int):Observable<List<FullDish>> {
        val dishesRepository
                = SearchRepositoryProvider.provideAtLeastOneSearchRepositoryFromTechnopolis()
        return dishesRepository.getDishes( ingredients = ingredients,
                                            startPosition = startPosition,
                                            countOfIngredients = countOfIngredients,
                                            searchType = searchType)
                                .map {it.recipes}
                                .map{recipe ->
                                    return@map recipe.map{
                                        dish -> migrateDishFromTechnopolisApiToFullDish(dish)
                                    }
                                }
    }

    private fun migrateDishFromTechnopolisApiToFullDish(dish: DishFromTechnopolisAPI):FullDish {
        val fullDish = FullDish()

        fullDish.urlImageRecipe = dish.imageUrl
        fullDish.nameDish = dish.name.capitalize()
        fullDish.ingredients = ingredientsFromTechnopolisAPiToFullDishIngredients(dish.ingredients)
        fullDish.descriptionDish = dish.description
        fullDish.recipe = dish.directions.toStringWithEnter()

        fullDish.isFavorite = DishRepository(null).isDishFavorite(fullDish)
        return fullDish
    }



    private fun ingredientsFromTechnopolisAPiToFullDishIngredients(ingredients: List<Ingredients>): MutableList<String> {
        val returnIngredientsList = mutableListOf<String>()
        ingredients.forEach{returnIngredientsList.add(it.ingredient.name + " " + it.amount)}
        return returnIngredientsList
    }

    /**
     * Take dishes from edamam.com
     */
    private fun getDishesFromEdamamAPI(ingredients: ArrayList<String>, startPosition: String):Observable<List<FullDish>> {
        val dishesRepository = SearchRepositoryProvider.provideSearchRepositoryFromEdamam()
        return dishesRepository.getDishes("8c1782dc",
                                          "e90b240c2c8118117d42cba520b31835",
                                                   ingredients, startPosition)
                                .map {it.hits}
                                .map{recipe ->
                                        return@map recipe.map{
                                            dish -> migrateDishModelToFullDish(dish.recipe)
                                        }
                                }

    }

    /**
     * Convert Dish model to FullDish model.
     * Set title, imageUrl.
     * @param dishModel - recipe model to convert.
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

}
