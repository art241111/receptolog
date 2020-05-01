package ru.art241111.dish_recipes.view.searchDishActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.ActionMenuViewBindingAdapter
import androidx.lifecycle.ViewModelProviders
import ru.art241111.dish_recipes.R
import ru.art241111.dish_recipes.databinding.ActivitySearchDishBinding
import ru.art241111.dish_recipes.view_models.SearchDishViewModel

class SearchDishActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySearchDishBinding
//    private val repositoryRecyclerViewAdapter = RepositoryRecyclerViewAdapter(arrayListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_search_dish
        )
        val viewModel = ViewModelProviders.of(this).get(SearchDishViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()

//        binding.repositoryRv.layoutManager = LinearLayoutManager(this)
//        binding.repositoryRv.adapter = repositoryRecyclerViewAdapter
//        viewModel.repositories.observe(this,
//                Observer<ArrayList<Repository>> { it?.let{ repositoryRecyclerViewAdapter.replaceData(it)} })

    }
//
//    override fun onItemClick(position: Int) {
//        TODO("Not yet implemented")
//    }


}