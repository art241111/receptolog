package ru.art241111.dish_recipes.view.searchDishActivity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import ru.art241111.dish_recipes.R
import ru.art241111.dish_recipes.databinding.ActivitySearchDishBinding
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