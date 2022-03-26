package com.karetolabs.cinemapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.karetolabs.cinemapp.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var activityFavoriteBinding: ActivityFavoriteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)
    }
}