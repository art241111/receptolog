package ru.art241111.dish_recipes.view.screenSearchDish


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ru.art241111.dish_recipes.R
import ru.art241111.dish_recipes.databinding.FragmentSearchDishBinding
import ru.art241111.dish_recipes.view.AppActivity
import ru.art241111.dish_recipes.view_models.SearchDishViewModel

/**
 * Main activity.
 * Activation for searching recipes by ingredients.
 * @author Artem Geraimov.
 */
class SearchDishFragment : Fragment() {
    private lateinit var binding: FragmentSearchDishBinding
    private lateinit var viewModel: SearchDishViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(activity as AppActivity).get(SearchDishViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_search_dish, container, false)

        binding.viewModel = viewModel
        binding.executePendingBindings()

        return binding.root
    }
}