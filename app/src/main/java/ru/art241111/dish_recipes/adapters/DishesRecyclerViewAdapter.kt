package ru.art241111.dish_recipes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.databinding.RecyclerViewItemBinding

/**
 * RecyclerView adapter.
 * @author Artem Geraimov.
 */
class DishesRecyclerViewAdapter(private var items: ArrayList<FullDish>,
                                private var listener: OnItemClickListener)
                                : RecyclerView.Adapter<DishesRecyclerViewAdapter.ViewHolder>() {

    /**
     * link data.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    /**
     * Interface to work with button click.
     */
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    /**
     * Create items.
     */
    class ViewHolder(private var binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dish: FullDish, listener: OnItemClickListener?) {
            binding.dish = dish
            if (listener != null) {
                binding.root.setOnClickListener { listener.onItemClick(layoutPosition) }
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
}