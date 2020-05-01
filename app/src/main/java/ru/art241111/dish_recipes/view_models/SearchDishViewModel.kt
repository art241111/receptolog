package ru.art241111.dish_recipes.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.art241111.dish_recipes.managers.NetManager
import ru.art241111.dish_recipes.models.DishRepository

class SearchDishViewModel(application: Application)
                             : AndroidViewModel(application) {

    var gitRepoRepository: DishRepository = DishRepository(NetManager(getApplication()))
}