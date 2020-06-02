package ru.art241111.dish_recipes.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_app.*
import ru.art241111.dish_recipes.R


class AppActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        // Set toolbar
        setSupportActionBar(toolbar)

        // Reference to the displayed fragment
        // Here my_nav_host_fragment is an ID of the fragment tag in your
        // content_main.xml with android:name="androidx.navigation.fragment.NavHostFragment"
        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return

        // Get the navigation controller
        // From documentation:
        // NavController manages app navigation within a {@link NavHost}
        val navController = host.navController


        // Link the toolbar to the navigation graph, so that the back arrow appears
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    /**
     * Initialize the contents of the Activity's standard options menu
     * This is only called once, the first time the options menu is displayed.
     * @param menu - The options menu in which you place your items.
     * @return You must return true for the menu to be displayed; if you return
     * false it will not be shown.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.user_info_menu, menu)
        return true
    }

    /**
     * Installing NavController on the Bottom menu
     * @param item - NavController associated with navigation
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        return item.onNavDestinationSelected(findNavController(R.id.my_nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }

}