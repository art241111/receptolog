package ru.art241111.dish_recipes.view.searchDishActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ru.art241111.dish_recipes.R
import ru.art241111.dish_recipes.adapters.DishesRecyclerViewAdapter
import ru.art241111.dish_recipes.databinding.ActivitySearchDishBinding
import ru.art241111.dish_recipes.view.recipeActivity.RecipeActivity
import ru.art241111.dish_recipes.view_models.SearchDishViewModel

class SearchDishActivity : AppCompatActivity(), DishesRecyclerViewAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // A binding with layout
        val binding:ActivitySearchDishBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_search_dish
        )

        // Add viewModel to binding
        val viewModel = ViewModelProviders.of(this).get(SearchDishViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        // Customization RecycleView: set layoutManager, adapter, data.
        customizationRecycleView(binding, viewModel)
    }

    /**
     * customization RecycleView: set layoutManager, adapter, data.
     * @param binding - link to the layout,
     * @param viewModel - link to the viewModel.
     */
    private fun customizationRecycleView(binding:ActivitySearchDishBinding, viewModel: SearchDishViewModel) {
        val dishesRecyclerViewAdapter = DishesRecyclerViewAdapter(arrayListOf(), this)
        binding.rvDish.layoutManager = LinearLayoutManager(this)
        binding.rvDish.adapter = dishesRecyclerViewAdapter
        viewModel.dishes.observe(this,
                Observer{ it?.let{ dishesRecyclerViewAdapter.replaceData(it)} })

        viewModel.loadDishes()
    }

    /**
     * this method works when the user clicks on an element RecycleView.
     * @param position - the position of the item on which the user clicked.
     */
    override fun onItemClick(position: Int) {
        val viewModel = ViewModelProviders.of(this).get(SearchDishViewModel::class.java)
        val intent = Intent(this, RecipeActivity::class.java)
        intent.putExtra("Dish", viewModel.dishes.value?.get(position))

        this.startActivity(intent)
    }


}