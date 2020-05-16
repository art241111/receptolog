package ru.art241111.dish_recipes

import android.app.Application
import ru.art241111.dish_recipes.helpers.PreferenceHelper


class DishApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        DishApplication.prefHelper = PreferenceHelper(this)
    }

    companion object {
        lateinit var prefHelper: PreferenceHelper
            private set
    }
}