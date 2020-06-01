package ru.art241111.dish_recipes.view.favoriteDishes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import ru.art241111.dish_recipes.R
import ru.art241111.dish_recipes.databinding.FavoriteDishesFragmentBinding
import ru.art241111.dish_recipes.view.AppActivity
import ru.art241111.dish_recipes.view_models.SearchDishViewModel

class FavoriteDishesFragment : Fragment() {
    /**
     * Start point of favorite fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FavoriteDishesFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.favorite_dishes_fragment, container, false)

        binding.executePendingBindings()

        return binding.root
    }
}