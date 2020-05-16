package ru.art241111.dish_recipes.adapters.dishesRecyclerViewAdapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.databinding.RecyclerViewItemBinding
import ru.art241111.dish_recipes.protocols.onClickFavoriteButton

/**
 * RecyclerView adapter.
 * @author Artem Geraimov.
 */
class DishesRecyclerViewAdapter(private var items: ArrayList<FullDish>,
                                private var itemListener: OnItemClickListener,
                                private var favoriteButtonListener: onClickFavoriteButton,
                                private var end: OnDataEnd)
                                : RecyclerView.Adapter<DishesRecyclerViewAdapter.ViewHolder>() {
    /**
     * link data.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], itemListener, favoriteButtonListener)

    /**
     * Set size
     */
    override fun getItemCount(): Int = items.size

    /**
     * Create items.
     */
    private lateinit var bindingRe:RecyclerViewItemBinding
    class ViewHolder(private var binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dish: FullDish, listener: OnItemClickListener?, favoriteButtonListener:onClickFavoriteButton?) {
            binding.dish = dish
            binding.isFavorite = dish.isFavorite
            if (listener != null) {
                binding.root.setOnClickListener { listener.onItemClick(layoutPosition) }
            }

            if (favoriteButtonListener != null) {
                binding.ivFavorite.setOnClickListener {
                    binding.isFavorite = !(binding.isFavorite!!)
                    favoriteButtonListener.onClickFavoriteButton(position = layoutPosition)
                }
            }
            binding.executePendingBindings()
        }
    }

    /**
     * Create new item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    /**
     * Data refresh.
     */
    fun replaceData(arrayList: ArrayList<FullDish>) {
        items = arrayList
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        val layoutPosition = holder.layoutPosition

        if((layoutPosition > (items.size - 2))){
            end.onDataEnd()
        }
    }
}