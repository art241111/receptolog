package ru.art241111.dish_recipes.view.viewDishActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.art241111.dish_recipes.DishApplication
import ru.art241111.dish_recipes.R
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.databinding.ActivityViewDishBinding
import ru.art241111.dish_recipes.view.viewDishActivity.fragments.IngredientsAndRecipeInfoFragment
import ru.art241111.dish_recipes.view.viewDishActivity.fragments.MainInformationFragment


/**
 * Activate to show recipes in more detail.
 * @author Artem Geraimov.
 */
class ViewDishActivity : AppCompatActivity() {
    // binding with layout.
    private lateinit var binding: ActivityViewDishBinding

    /**
     * Take data from intent.
     * If intent null, return empty object.
     */
    private fun getDataFromIntent(): FullDish {
        val intent = intent
        return if (intent != null) {
            intent.getParcelableExtra("Dish")!!
        } else FullDish()
    }

    /**
     * Start activity, draw layout.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // A binding with layout.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_dish)

        // Loading dish data to layout.
        val dish = getDataFromIntent()

        //Add fragment with main information about dishes
        addMainInfoFragment(dish)

        addIngredientsAndRecipeFragment(dish)

    }

    private fun addIngredientsAndRecipeFragment(dish: FullDish) {
        // Get instance FragmentTransaction.
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        // Create fragment.
        val fragment = IngredientsAndRecipeInfoFragment.newInstance(dish)

        // Add fragment to LinearLayout.
        fragmentTransaction.add(R.id.ll_main, fragment)
        fragmentTransaction.commit()
    }

    /**
     * Add fragment with main information about dishes:
     * Image, name, description
     * @param dish - data for uploading information about the dish
     */
    private fun addMainInfoFragment(dish: FullDish) {
        // Get instance FragmentTransaction.
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        // Create fragment.
        val fragment = MainInformationFragment.newInstance(dish)

        // Add fragment to LinearLayout.
        fragmentTransaction.add(R.id.ll_main, fragment)
        fragmentTransaction.commit()
    }


}