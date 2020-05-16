package ru.art241111.dish_recipes.view.favoriteDishes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.art241111.dish_recipes.R
import ru.art241111.dish_recipes.adapters.dishesRecyclerViewAdapter.DishesRecyclerViewAdapter
import ru.art241111.dish_recipes.adapters.dishesRecyclerViewAdapter.OnDataEnd
import ru.art241111.dish_recipes.adapters.dishesRecyclerViewAdapter.OnItemClickListener
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.databinding.FragmentRecyclerViewForFavoriteDishesBinding
import ru.art241111.dish_recipes.models.DishRepository
import ru.art241111.dish_recipes.protocols.onClickFavoriteButton
import ru.art241111.dish_recipes.view.AppActivity
import ru.art241111.dish_recipes.view_models.FavoriteDishesViewModel
import ru.art241111.dish_recipes.view_models.SearchDishViewModel
import ru.art241111.dish_recipes.view_models.protocols.UpdateFavorite

/**
 * Fragment show dishes in recyclerView
 *
 * A simple [Fragment] subclass.
 * Use the [RecyclerViewForFavoriteDishesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecyclerViewForFavoriteDishesFragment : Fragment(), OnItemClickListener, OnDataEnd, onClickFavoriteButton {
    private lateinit var binding: FragmentRecyclerViewForFavoriteDishesBinding
    private lateinit var viewModel:FavoriteDishesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(activity as AppActivity).get(FavoriteDishesViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_recycler_view_for_favorite_dishes, container, false)

        binding.viewModel = viewModel
        binding.executePendingBindings()

        // Customization RecycleView: set layoutManager, adapter, data.
        customizationRecycleView()

        return binding.root
    }

    /**
     * Customization RecycleView: set layoutManager, adapter, data.
     */
    private fun customizationRecycleView() {
        val dishesRecyclerViewAdapter =
                DishesRecyclerViewAdapter(arrayListOf(),
                               this,
                        this,
                                     this)

        binding.rvDish.layoutManager = LinearLayoutManager(activity)
        binding.rvDish.adapter = dishesRecyclerViewAdapter
        viewModel.dishes.observe(activity as AppActivity,
                Observer{
                    it?.let{ dishesRecyclerViewAdapter.replaceData(it as ArrayList<FullDish>)}
                })

        viewModel.loadFavoriteDishes()

    }

    /**
     * This method works when the user clicks on an element RecycleView.
     * @param position - the position of the item on which the user clicked.
     */
    override fun onItemClick(position: Int) {
        val dish: FullDish? = viewModel.dishes.value?.get(position)
        val bundle = bundleOf("selected_dish" to dish)

        findNavController().navigate(R.id.viewDishActivity, bundle)
    }

    /**
     * If user click on favorite button on recycler view
     * @param position - position of element
     */
    override fun onClickFavoriteButton(position: Int) {
        val viewModelWithLiveData
                = ViewModelProviders.of(activity as AppActivity).get(SearchDishViewModel::class.java)

        val dish: FullDish? = viewModel.dishes.value?.get(position)

        if (dish != null) {
            dish.isFavorite = !dish.isFavorite
            if(dish.isFavorite){
                DishRepository(null).addFavoriteDishes(dish)
            } else {
                DishRepository(null).removeFavoriteDishes(dish)
            }
            (viewModelWithLiveData as UpdateFavorite).updateFavoriteAtOneDish(dish)
        }
    }

    override fun onDataEnd() {
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RecyclerViewForDishesFragment.
         */
        @JvmStatic
        fun newInstance() = RecyclerViewForFavoriteDishesFragment()
    }


}