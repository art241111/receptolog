package ru.art241111.dish_recipes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.databinding.RecyclerViewItemBinding

class DishesRecyclerViewAdapter(private var items: ArrayList<FullDish>,
                                private var listener: OnItemClickListener)
                                : RecyclerView.Adapter<DishesRecyclerViewAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ViewHolder(private var binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dish: FullDish, listener: OnItemClickListener?) {
            binding.dish = dish
            if (listener != null) {
                binding.root.setOnClickListener { _ -> listener.onItemClick(layoutPosition) }
            }

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = RecyclerViewItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    fun replaceData(arrayList: ArrayList<FullDish>) {
        items = arrayList
        notifyDataSetChanged()
    }
}