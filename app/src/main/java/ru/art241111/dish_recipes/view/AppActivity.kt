package ru.art241111.dish_recipes.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.art241111.dish_recipes.R
import ru.art241111.dish_recipes.view.searchDishActivity.SearchDishFragment

class AppActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, SearchDishFragment.newInstance())
                    .commit()
        }
    }
}