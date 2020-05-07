package ru.art241111.dish_recipes.view.searchDishActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ru.art241111.dish_recipes.R
import ru.art241111.dish_recipes.adapters.DishesRecyclerViewAdapter
import ru.art241111.dish_recipes.databinding.ActivitySearchDishBinding
import ru.art241111.dish_recipes.view.dishActivity.DishActivity
import ru.art241111.dish_recipes.view_models.SearchDishViewModel

/**
 * Main activity.
 * Activation for searching recipes by ingredients.
 * @author Artem Geraimov.
 */
class SearchDishActivity : AppCompatActivity(), DishesRecyclerViewAdapter.OnItemClickListener {
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

        // Set click Listener on add button.
        onAddButtonClickListener()

        // Ingredients recovery after app death.
        recoveryIngredients()
    }

    /**
     * Ingredients recovery after app death.
     */
    private fun recoveryIngredients() {
        for (ingredient in viewModel.ingredients){
            addIngredientToFlowLayout(ingredient)
        }
    }

    /**
     * Click listener of add button.
     */
    private fun onAddButtonClickListener() {
        binding.ibAddIngredients.setOnClickListener{
            // Add ingredients to array.
            viewModel.addIngredient(binding.etIngredients.text.toString())

            // Create ingredient view.
            addIngredientToFlowLayout(binding.etIngredients.text.toString())

            // Clear EditText.
            binding.etIngredients.text.clear()
        }
    }

    /**
     * Add ingredient to flow layout.
     * @param ingredientName - name of ingredient.
     */
    private fun addIngredientToFlowLayout(ingredientName: String) {
        val inflater = layoutInflater
        val ingredient = inflater.inflate(R.layout.ingredient, null)

        // Customization ingredients view.
        fillingInDataToIngredientView(ingredient, ingredientName)

        // Add ingredient to FlowLayout.
        binding.flListIngredients.addView(ingredient)
    }

    /**
     * Filling in the view data.
     * @param ingredient - new view.
     * @param ingredientName - name of ingredient.
     */
    private fun fillingInDataToIngredientView(ingredient: View, ingredientName: String) {
        // Set ingredients name.
        val textView = ingredient.findViewById<TextView>(R.id.tv_name_ingredient)
        textView.text = ingredientName

        // Add click listener om delete button.
        val imageView = ingredient.findViewById<ImageView>(R.id.btn_delete)
        imageView.setOnClickListener {
            binding.flListIngredients.removeView(ingredient)
            viewModel.deleteIngredient(ingredientName)
        }
    }

    /**
     * Customization RecycleView: set layoutManager, adapter, data.
     */
    private fun customizationRecycleView() {
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
        val intent = Intent(this, DishActivity::class.java)
        intent.putExtra("Dish", viewModel.dishes.value?.get(position))

        this.startActivity(intent)
    }
}