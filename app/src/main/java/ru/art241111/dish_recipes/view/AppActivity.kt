package ru.art241111.dish_recipes.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import ru.art241111.dish_recipes.R
import ru.art241111.dish_recipes.models.remoteDataSource.providers.searchDishByIngredientsProvider.SearchDishes
import ru.art241111.dish_recipes.view.searchDishActivity.SearchDishFragment

class AppActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
    }
}