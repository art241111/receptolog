package ru.art241111.dish_recipes.view.viewDishActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import ru.art241111.dish_recipes.R
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.databinding.FragmentViewDishBinding
import ru.art241111.dish_recipes.view.AppActivity
import ru.art241111.dish_recipes.view.viewDishActivity.fragments.IngredientsAndRecipeInfoFragment
import ru.art241111.dish_recipes.view.viewDishActivity.fragments.MainInformationFragment

/**
 * The fragment initialization parameters:
 * ARG_SELECTED_DISH - Parameter for the instance that
 * passes the dish that the user selected
 */

private const val ARG_SELECTED_DISH = "selected_dish"

/**
 * Activate to show recipes in more detail.
 * @author Artem Geraimov.
 */
class ViewDishActivity : Fragment() {
    // binding with layout.
    private lateinit var binding: FragmentViewDishBinding

    /**
     * Start activity and draw layout
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_view_dish, container, false)

        binding.executePendingBindings()

        // Loading dish data to layout.
        var dish = FullDish()
        arguments?.let {
            dish = it.getParcelable(ARG_SELECTED_DISH)!!
        }
        //Add fragment with main information about dishes
        addMainInfoFragment(dish)

        //Add fragment with information about ingredients and recipes
        addIngredientsAndRecipeFragment(dish)

        return binding.root
    }

    /**
     * Add fragment with main information about dishes:
     * Image, name, description
     * @param dish - data for uploading information about the dish
     */
    private fun addMainInfoFragment(dish: FullDish) {

        (activity as AppActivity).supportFragmentManager
                .beginTransaction()
                .add(R.id.ll_main, MainInformationFragment.newInstance(dish))
                .commit()
    }

    /**
     * Add fragment with information about ingredients and recipes
     * @param dish - data for uploading information about the dish
     */
    private fun addIngredientsAndRecipeFragment(dish: FullDish) =
            (activity as AppActivity).supportFragmentManager
                    .beginTransaction()
                    .add(R.id.ll_main, IngredientsAndRecipeInfoFragment.newInstance(dish))
                    .commit()
}