package ru.art241111.dish_recipes.models.localDataSource.favoriteDishes


import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.art241111.dish_recipes.DishApplication
import ru.art241111.dish_recipes.data.FullDish
import ru.art241111.dish_recipes.models.localDataSource.favoriteDishes.protocols.getAllFavoriteDishes
import ru.art241111.dish_recipes.models.localDataSource.favoriteDishes.protocols.isDishFavorite
import ru.art241111.dish_recipes.models.localDataSource.favoriteDishes.protocols.removeFavoriteDishes
import ru.art241111.dish_recipes.models.localDataSource.favoriteDishes.protocols.saveFavoriteDishes


private const val APP_PREFERENCES = "preferences_favorite_dish"
private const val APP_FAVORITE_DISH = "favorite_dish"

class FavoriteDishes() : saveFavoriteDishes, getAllFavoriteDishes,
                         removeFavoriteDishes, isDishFavorite {

    /**
     * Add dish to favorite list
     * @param dish - the dish that you want to add
     */
    override fun addFavoriteDishes(dish: FullDish) {
        // Create access to SharedPreferences.
        val pref = DishApplication.prefHelper.customPrefs(APP_PREFERENCES)
        val editor = pref.edit()

        // Read JSON from SharedPreferences.
        val dishes = readArrayFromSharedPreferences(pref)

        // Add dish to saved array.
        dishes.add(dish)

        Log.d("change_shared", "add $dishes.toString()")

        // Save favorite dish in SharedPreferences.
        editor.putString(APP_FAVORITE_DISH,Gson().toJson(dishes))
                .apply()
    }

    /**
     * Remove dish to favorite list
     * @param dish - the dish that you want to remove
     */
    override fun removeFavoriteDishes(dish: FullDish) {
        // Create access to SharedPreferences.
        val pref = DishApplication.prefHelper.customPrefs(APP_PREFERENCES)
        val editor = pref.edit()

        // Read JSON from SharedPreferences.
        val dishes = readArrayFromSharedPreferences(pref)

        // Add dish to saved array.
        dishes.remove(dish)

        Log.d("change_shared", "remove $dishes.toString()")
        // Save favorite dish in SharedPreferences.
        editor.putString(APP_FAVORITE_DISH,Gson().toJson(dishes))
                .apply()
    }

    /**
     * Getting all your favorite dishes from SharedPreferences
     * @return list of favorite dishes
     */
    override fun getAllFavoriteDishes(): List<FullDish> {
        // Create access to SharedPreferences.
        val pref = DishApplication.prefHelper.customPrefs(APP_PREFERENCES)

        // Return array with favorite dishes.
        return readArrayFromSharedPreferences(pref)
    }

    /**
     * Checking whether the dish is in the favorite
     * @return true, if dish is in the favorite and false if is not
     */
    override fun isDishFavorite(dish: FullDish): Boolean {
        // Create access to SharedPreferences.
        val pref = DishApplication.prefHelper.customPrefs(APP_PREFERENCES)

        // Return array with favorite dishes.
        return readArrayFromSharedPreferences(pref).contains(dish)
    }

    private fun readArrayFromSharedPreferences(pref: SharedPreferences):MutableList<FullDish>{
        // Read JSON from SharedPreferences.
        val jsonRead = pref.getString(APP_FAVORITE_DISH,
                Gson().toJson(listOf<FullDish>(FullDish())))

        // Converting json to array.
        var dishes: MutableList<FullDish>? = Gson().fromJson(jsonRead,
                object : TypeToken<MutableList<FullDish>>() {}.type)

        // Processing a null array.
        if (dishes == null){
            dishes = mutableListOf()
        }

        return dishes
    }


}