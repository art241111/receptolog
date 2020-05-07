package ru.art241111.dish_recipes.view.searchDishActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ru.art241111.dish_recipes.R
import ru.art241111.dish_recipes.adapters.DishesRecyclerViewAdapter
import ru.art241111.dish_recipes.databinding.ActivitySearchDishBinding
import ru.art241111.dish_recipes.view_models.SearchDishViewModel

class SearchDishActivity : AppCompatActivity(), DishesRecyclerViewAdapter.OnItemClickListener {

    private lateinit var binding:ActivitySearchDishBinding
    private val dishesRecyclerViewAdapter = DishesRecyclerViewAdapter(arrayListOf(), this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_search_dish
        )
        val viewModel = ViewModelProviders.of(this).get(SearchDishViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.rvDish.layoutManager = LinearLayoutManager(this)
        binding.rvDish.adapter = dishesRecyclerViewAdapter
        viewModel.repositories.observe(this,
                Observer{ it?.let{ dishesRecyclerViewAdapter.replaceData(it)} })

        viewModel.loadDishes()

    }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }


}