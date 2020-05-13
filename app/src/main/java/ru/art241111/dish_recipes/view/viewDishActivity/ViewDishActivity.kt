package ru.art241111.dish_recipes.view.viewDishActivity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.art241111.dish_recipes.R
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.databinding.ActivityViewDishBinding
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
    private lateinit var binding: ActivityViewDishBinding

    /**
     * Take data from intent.
     * If intent null, return empty object.
     */
    private fun getDataFromIntent(): FullDish {
        return getDataFromIntent() ?: FullDish()
    }

    /**
     * Start activity, draw layout.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // A binding with layout.
        binding = DataBindingUtil.setContentView(activity as AppActivity, R.layout.activity_view_dish)

        // Loading dish data to layout.
        //val dish = getDataFromIntent()
        var dish:FullDish = FullDish()
        arguments?.let {
            dish = it.getParcelable(ARG_SELECTED_DISH)!!
        }
        //Add fragment with main information about dishes
        addMainInfoFragment(dish)

        //Add fragment with information about ingredients and recipes
        addIngredientsAndRecipeFragment(dish)
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





    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param selectedDish The dish that the user selected.
         * @return A new instance of fragment MainInformationFragment.
         */
        @JvmStatic
        fun newInstance(selectedDish: FullDish?) =
                ViewDishActivity().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARG_SELECTED_DISH, selectedDish)
                    }
                }
    }
}