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
import ru.art241111.dish_recipes.view.searchDishActivity.fragments.SearchDishesByIngredientsFragment
import ru.art241111.dish_recipes.view.viewDishActivity.ViewDishActivity
import ru.art241111.dish_recipes.view.viewDishActivity.fragments.IngredientsAndRecipeInfoFragment
import ru.art241111.dish_recipes.view_models.SearchDishViewModel


/**
 * Main activity.
 * Activation for searching recipes by ingredients.
 * @author Artem Geraimov.
 */
class SearchDishActivity : AppCompatActivity(), OnItemClickListener, OnDataEnd {
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

        // Customization RecycleView: set layoutManager, adapter, data.
        customizationRecycleView()

        // Add fragment for search dishes by ingredients
        addFragmentSearchDishesByIngredients()
    }

    private fun addFragmentSearchDishesByIngredients() {
        // Get instance FragmentTransaction.
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        // Create fragment.
        val fragment = SearchDishesByIngredientsFragment.newInstance()

        // Add fragment to LinearLayout.
        fragmentTransaction.add(R.id.ll_main_search_dish_activity, fragment)
        fragmentTransaction.commit()
    }

    /**
     * Customization RecycleView: set layoutManager, adapter, data.
     */
    private fun customizationRecycleView() {
        val dishesRecyclerViewAdapter = DishesRecyclerViewAdapter(arrayListOf(), this,this)

        binding.rvDish.layoutManager = LinearLayoutManager(this)
        binding.rvDish.adapter = dishesRecyclerViewAdapter
        viewModel.dishes.observe(this,
                Observer{ it?.let{ dishesRecyclerViewAdapter.replaceData(it)} })

        if(viewModel.isApplicationCreateFirst){
            viewModel.loadDishesWhenUserAddNewIngredientOrStartApplication()
            viewModel.isApplicationCreateFirst = false
        }
    }

    /**
     * this method works when the user clicks on an element RecycleView.
     * @param position - the position of the item on which the user clicked.
     */
    override fun onItemClick(position: Int) {
        val intent = Intent(this, ViewDishActivity::class.java)
        intent.putExtra("Dish", viewModel.dishes.value?.get(position))

        this.startActivity(intent)
    }

    override fun onDataEnd() {
        viewModel.loadDishesWhenOnScreenEnd()
        Log.d("end recyler", "end")
    }
}