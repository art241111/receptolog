package ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsFromTechnopolisApi.dataModel

data class DishFromTechnopolisAPI (val id : Int,
                                 val name : String,
                                 val imageUrl : String,
                                 val source : String,
                                 val description : String,
                                 val ingredients : List<Ingredients>,
                                 val directions : List<String>)