package ru.art241111.dish_recipes.models.localDataSource

import android.os.Handler

class GitDishLocalDataSource {
    fun getRepositories(onRepositoryReadyCallback: OnDishLocalReadyCallback) {
        var arrayList =


        Handler().postDelayed({ onRepositoryReadyCallback.onLocalDataReady(arrayList) }, 2000)
    }

    fun saveRepositories(arrayList: ArrayList<Repository>){
        //todo save repositories in DB
    }
}