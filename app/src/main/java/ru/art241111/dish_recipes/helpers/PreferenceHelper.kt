package ru.art241111.dish_recipes.helpers

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(val context: Context){
    fun customPrefs(name: String): SharedPreferences
            = context.getSharedPreferences(name, Context.MODE_PRIVATE)
}