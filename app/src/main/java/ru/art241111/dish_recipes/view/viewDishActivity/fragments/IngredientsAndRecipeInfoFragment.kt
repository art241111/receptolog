package ru.art241111.dish_recipes.view.viewDishActivity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ru.art241111.dish_recipes.R
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.databinding.FragmentIngredientsAndRecipeInfoBinding

/**
 * The fragment initialization parameters:
 * ARG_SELECTED_DISH - Parameter for the instance that
 * passes the dish that the user selected
 */

private const val ARG_SELECTED_DISH = "selected_dish"

/**
 * Fragment show ingredients and recipe:
 *
 * A simple [Fragment] subclass.
 * Use the [IngredientsAndRecipeInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 * @author Artem Gerasimov
 */
class IngredientsAndRecipeInfoFragment : Fragment() {
    private var dish = FullDish()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dish = it.getParcelable(ARG_SELECTED_DISH)!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentIngredientsAndRecipeInfoBinding>(inflater,
                R.layout.fragment_ingredients_and_recipe_info, container, false)

        binding.fullDish = dish

        // Setup and customization tab host.
        setupTabHost(binding)

        return binding.root
    }

    /**
     * Setup and customization tab host.
     */
    private fun setupTabHost(binding: FragmentIngredientsAndRecipeInfoBinding) {
        binding.tabHost.setup()

        var tabSpec = binding.tabHost.newTabSpec("tag1")
        tabSpec.setContent(R.id.tab1)
        tabSpec.setIndicator(getString(R.string.ingredients))

        binding.tabHost.addTab(tabSpec)

        tabSpec = binding.tabHost.newTabSpec("tag2")
        tabSpec.setContent(R.id.tab2)
        tabSpec.setIndicator(getString(R.string.recipes))
        binding.tabHost.addTab(tabSpec)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param dish The dish that the user selected.
         * @return A new instance of fragment IngredientsAndRecipeInfoFragment.
         */
        @JvmStatic
        fun newInstance(dish: FullDish) =
                IngredientsAndRecipeInfoFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARG_SELECTED_DISH, dish)
                    }
                }
    }
}