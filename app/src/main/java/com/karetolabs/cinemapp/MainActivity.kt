package com.karetolabs.cinemapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import com.bumptech.glide.Glide
import com.karetolabs.cinemapp.TopRated.TopRatedFragment
import com.karetolabs.cinemapp.databinding.ActivityMainBinding
import com.karetolabs.cinemapp.discover.DiscoverFragment
import com.karetolabs.cinemapp.popular.PopularFragment

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setSupportActionBar(activityMainBinding.toolbarHome)
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
        }
        val drawerToggle = ActionBarDrawerToggle(
            this,
            activityMainBinding.drawerHome,
            activityMainBinding.toolbarHome,
            R.string.app_name,
            R.string.app_name
        )
        drawerToggle.syncState()
        activityMainBinding.drawerHome.addDrawerListener(drawerToggle)
        activityMainBinding.naviewHome.getHeaderView(0).findViewById<TextView>(R.id.tvUserName).text = "Daniel"
        val imageview = activityMainBinding.naviewHome.getHeaderView(0).findViewById<ImageView>(R.id.ivAvatar)
        Glide.with(this)
            .load("https://images-eu.ssl-images-amazon.com/images/I/5117ZW5600L.__AC_SX300_SY300_QL70_ML2_.jpg",)
            .into(imageview)
        activityMainBinding.naviewHome.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.actionDiscover -> {
                    supportFragmentManager.beginTransaction()
                        .replace(activityMainBinding.fcvSection.id,
                            DiscoverFragment())
                        .commit()
                }
                R.id.actionFavorites -> {
                    startActivity(Intent(this, FavoriteActivity::class.java))
                }
                R.id.actionPopular -> {
                    supportFragmentManager.beginTransaction()
                        .replace(activityMainBinding.fcvSection.id,
                            PopularFragment())
                        .commit()
                }
                R.id.actionTopRated -> {
                    supportFragmentManager.beginTransaction()
                        .replace(activityMainBinding.fcvSection.id,
                            TopRatedFragment())
                        .commit()
                }
            }
            activityMainBinding.drawerHome.closeDrawers()
            false
        }

        activityMainBinding.btnFavorite.setOnClickListener {
           // startActivity(Intent(this, FavoriteActivity::class.java))
            activityMainBinding.drawerHome.openDrawer(Gravity.RIGHT)
        }

    }
}