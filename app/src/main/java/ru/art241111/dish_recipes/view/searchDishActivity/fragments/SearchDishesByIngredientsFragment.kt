package ru.art241111.dish_recipes.view.searchDishActivity.fragments

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import ru.art241111.dish_recipes.R
import ru.art241111.dish_recipes.databinding.FragmentSearchDishesByIngredientsBinding
import ru.art241111.dish_recipes.view.searchDishActivity.SearchDishActivity
import ru.art241111.dish_recipes.view_models.SearchDishViewModel

/**
 * Fragment show ingredient search
 *
 * A simple [Fragment] subclass.
 * Use the [SearchDishesByIngredientsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchDishesByIngredientsFragment : Fragment() {
    private lateinit var binding:FragmentSearchDishesByIngredientsBinding
    private lateinit var viewModel:SearchDishViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(activity as SearchDishActivity).get(SearchDishViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_search_dishes_by_ingredients, container, false)

        // Set click Listener on add button.
        onAddButtonClickListener()

        // Set click listener on keyboard enter
        onEnterKeyboardListener()

        // Ingredients recovery after app death.
        recoveryIngredients()

        return binding.root
    }

    /**
     * Method to add and search ingredients
     */
    private fun addIngredients(){
        // Add ingredients to array.
        viewModel.addIngredient(binding.etIngredients.text.toString())

        // Create ingredient view.
        addIngredientToFlowLayout(binding.etIngredients.text.toString())

        // Clear EditText.
        binding.etIngredients.text.clear()

        // Load new data
        viewModel.loadDishesWhenUserAddNewIngredientOrStartApplication()
    }

    /**
     * Set click listener on enter keyboard
     */
    private fun onEnterKeyboardListener() {
        binding.etIngredients.setOnKeyListener(
                View.OnKeyListener { _, keyCode, event ->
                    if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                        addIngredients()
                        return@OnKeyListener true
                    }
                    false
                }
        )
    }

    /**
     * Click listener of add button.
     */
    private fun onAddButtonClickListener() {
        binding.ibAddIngredients.setOnClickListener{
            addIngredients()
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
            viewModel.loadDishesWhenUserAddNewIngredientOrStartApplication()
        }
    }

    /**
     * Ingredients recovery after app death.
     */
    private fun recoveryIngredients() {
        for (ingredient in viewModel.ingredients){
            addIngredientToFlowLayout(ingredient)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SearchDishesByIngredientsFragment.
         */
        @JvmStatic
        fun newInstance() = SearchDishesByIngredientsFragment()
    }
}