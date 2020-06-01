package ru.art241111.dish_recipes.view.viewDishActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
    /**
     * Start activity and draw layout
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentViewDishBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_view_dish, container, false)

        // Loading dish data
        val dish = loadDishFromArguments()

        // Set name of dish in toolbar
        (activity?.findViewById(R.id.toolbar) as Toolbar).title = dish.nameDish

        //Add fragment with main information about dishes
        addFragment(MainInformationFragment.newInstance(dish))

        //Add fragment with information about ingredients and recipes
        addFragment(IngredientsAndRecipeInfoFragment.newInstance(dish))

        return binding.root
    }

    /**
     * Load data form arguments
     */
    private fun loadDishFromArguments(): FullDish{
        var dish = FullDish()
        arguments?.let {
            dish = it.getParcelable(ARG_SELECTED_DISH)!!
        }
        return dish
    }

    /**
     * Add fragment into view fragment:
     */
    private fun addFragment(fragment: Fragment){
        (activity as AppActivity).supportFragmentManager
                .beginTransaction()
                .add(R.id.ll_main, fragment)
                .commit()
    }
}