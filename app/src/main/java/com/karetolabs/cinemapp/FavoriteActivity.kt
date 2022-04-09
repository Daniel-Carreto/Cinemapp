package com.karetolabs.cinemapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.karetolabs.cinemapp.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity(), FavoriteFragment.FavoriteFragmentListener {

    private lateinit var activityFavoriteBinding: ActivityFavoriteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)
    }

    override fun addFavoriteEvent() {
        supportFragmentManager.beginTransaction()
            .replace(activityFavoriteBinding.containerFavorite.id, AddFavoriteFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun addDetailEvent(item: Favorite) {
        supportFragmentManager.beginTransaction()
            .replace(activityFavoriteBinding.containerFavorite.id,
            DetailFragment.newInstance(item.id?:0L))
            .addToBackStack(null)
            .commit()
    }
}