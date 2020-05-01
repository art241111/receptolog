package ru.art241111.dish_recipes.managers

import android.content.Context
import android.net.ConnectivityManager

/**
 * NetManager - Checks whether there is Internet access
 */

class NetManager(private var applicationContext: Context) {
    private var status: Boolean? = false

    val isConnectedToInternet: Boolean?
        get() {
            val conManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val ni = conManager.activeNetworkInfo
            return ni != null && ni.isConnected
        }
}