package ru.art241111.dish_recipes.view.searchDishActivity.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ru.art241111.dish_recipes.R
import ru.art241111.dish_recipes.adapters.dishesRecyclerViewAdapter.DishesRecyclerViewAdapter
import ru.art241111.dish_recipes.adapters.dishesRecyclerViewAdapter.OnDataEnd
import ru.art241111.dish_recipes.adapters.dishesRecyclerViewAdapter.OnItemClickListener
import ru.art241111.dish_recipes.databinding.FragmentRecyclerViewForDishesBinding
import ru.art241111.dish_recipes.databinding.FragmentSearchDishesByIngredientsBinding
import ru.art241111.dish_recipes.view.searchDishActivity.SearchDishActivity
import ru.art241111.dish_recipes.view.viewDishActivity.ViewDishActivity
import ru.art241111.dish_recipes.view_models.SearchDishViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerViewForDishesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecyclerViewForDishesFragment : Fragment(), OnItemClickListener, OnDataEnd {
    private lateinit var binding: FragmentRecyclerViewForDishesBinding
    private lateinit var viewModel:SearchDishViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(activity as SearchDishActivity).get(SearchDishViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_recycler_view_for_dishes, container, false)

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
        val dishesRecyclerViewAdapter = DishesRecyclerViewAdapter(arrayListOf(), this,this)

        binding.rvDish.layoutManager = LinearLayoutManager(activity)
        binding.rvDish.adapter = dishesRecyclerViewAdapter
        viewModel.dishes.observe(this,
                Observer{
                    it?.let{ dishesRecyclerViewAdapter.replaceData(it)}
                })

        if(viewModel.isApplicationCreateFirst){
            viewModel.loadDishesWhenUserAddNewIngredientOrStartApplication()
            viewModel.isApplicationCreateFirst = false
        }
    }

    /**
     * this method works when the user clicks on an element RecycleView.
     * @param position - the position of the item on which the user clicked.
     */
    override fun onItemClick(position: Int) {
        val intent = Intent(activity, ViewDishActivity::class.java)
        intent.putExtra("Dish", viewModel.dishes.value?.get(position))

        this.startActivity(intent)
    }

    override fun onDataEnd() {
        viewModel.loadDishesWhenOnScreenEnd()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RecyclerViewForDishesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                RecyclerViewForDishesFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}