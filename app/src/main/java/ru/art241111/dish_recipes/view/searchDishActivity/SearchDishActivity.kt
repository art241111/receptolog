package ru.art241111.dish_recipes.view.searchDishActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ru.art241111.dish_recipes.R
import ru.art241111.dish_recipes.adapters.dishesRecyclerViewAdapter.DishesRecyclerViewAdapter
import ru.art241111.dish_recipes.adapters.dishesRecyclerViewAdapter.OnDataEnd
import ru.art241111.dish_recipes.adapters.dishesRecyclerViewAdapter.OnItemClickListener
import ru.art241111.dish_recipes.databinding.ActivitySearchDishBinding
import ru.art241111.dish_recipes.view.searchDishActivity.fragments.RecyclerViewForDishesFragment
import ru.art241111.dish_recipes.view.searchDishActivity.fragments.SearchDishesByIngredientsFragment
import ru.art241111.dish_recipes.view.viewDishActivity.ViewDishActivity
import ru.art241111.dish_recipes.view.viewDishActivity.fragments.IngredientsAndRecipeInfoFragment
import ru.art241111.dish_recipes.view_models.SearchDishViewModel

/**
 * Main activity.
 * Activation for searching recipes by ingredients.
 * @author Artem Geraimov.
 */
class SearchDishActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchDishBinding
    private lateinit var viewModel: SearchDishViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // A binding with layout.
        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_search_dish
        )

        // Add viewModel to binding.
        viewModel = ViewModelProviders.of(this)
                                      .get(SearchDishViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}