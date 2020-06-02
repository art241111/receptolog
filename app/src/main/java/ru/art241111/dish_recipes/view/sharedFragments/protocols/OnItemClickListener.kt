package ru.art241111.dish_recipes.view.sharedFragments.protocols

import android.os.Parcelable

/**
 * Interface to work with button click.
 */
interface OnItemClickListener {
    fun onItemClick(position: Int)
}