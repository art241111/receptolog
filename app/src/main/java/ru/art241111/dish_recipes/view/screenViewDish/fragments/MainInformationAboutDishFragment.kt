package ru.art241111.dish_recipes.view.screenViewDish.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ru.art241111.dish_recipes.R
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.databinding.FragmentMainInformationAboutDishBinding
import ru.art241111.dish_recipes.models.DishRepository
import ru.art241111.dish_recipes.view.AppActivity
import ru.art241111.dish_recipes.view_models.SearchDishViewModel
import ru.art241111.dish_recipes.view_models.protocols.UpdateFavorite

/**
 * The fragment initialization parameters:
 * ARG_SELECTED_DISH - Parameter for the instance that
 * passes the dish that the user selected
 */

private const val ARG_SELECTED_DISH = "selected_dish"

/**
 * Fragment show base information about dish:
 * Image, name, description
 *
 * A simple [Fragment] subclass.
 * Use the [MainInformationFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 * @author Artem Gerasimov
 */
class MainInformationFragment : Fragment() {
    private var dish = FullDish()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dish = it.getParcelable(ARG_SELECTED_DISH)!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMainInformationAboutDishBinding>(inflater,
                R.layout.fragment_main_information_about_dish, container, false)

        binding.fullDish = dish

        // Set click listener on favorite button
        setClickListenerOnFavoriteButton(binding)

        return binding.root
    }

    /**
     * Method work when user click on favorite button
     */
    private fun setClickListenerOnFavoriteButton(binding: FragmentMainInformationAboutDishBinding) {
        binding.ivFavoriteMain.setOnClickListener {
            val viewModelWithLiveData
                    = ViewModelProviders.of(activity as AppActivity).get(SearchDishViewModel::class.java)

            // Change status
            DishRepository(null).changeFavoriteStatus(dish)

            // Data refresh
            (viewModelWithLiveData as UpdateFavorite).updateFavoriteAtOneDish(dish)
            binding.invalidateAll()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param selectedDish The dish that the user selected.
         * @return A new instance of fragment MainInformationFragment.
         */
        @JvmStatic
        fun newInstance(selectedDish: FullDish) =
                MainInformationFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARG_SELECTED_DISH, selectedDish)
                    }
                }
    }
}