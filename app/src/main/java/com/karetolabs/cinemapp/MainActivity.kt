package com.karetolabs.cinemapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.bumptech.glide.Glide
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.karetolabs.cinemapp.TopRated.TopRatedFragment
import com.karetolabs.cinemapp.databinding.ActivityMainBinding
import com.karetolabs.cinemapp.discover.DiscoverFragment
import com.karetolabs.cinemapp.popular.PopularFragment
import com.karetolabs.cinemapp.poster.PosterFragment
import com.karetolabs.cinemapp.upcomming.UpComingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setSupportActionBar(activityMainBinding.toolbarHome)
        firebaseAnalytics = Firebase.analytics
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
        activityMainBinding.naviewHome.getHeaderView(0)
            .findViewById<TextView>(R.id.tvUserName).text = "Daniel"
        val imageview =
            activityMainBinding.naviewHome.getHeaderView(0).findViewById<ImageView>(R.id.ivAvatar)
        Glide.with(this)
            .load("https://images-eu.ssl-images-amazon.com/images/I/5117ZW5600L.__AC_SX300_SY300_QL70_ML2_.jpg")
            .into(imageview)
        activityMainBinding.naviewHome.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.actionDiscover -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            activityMainBinding.fcvSection.id,
                            DiscoverFragment()
                        )
                        .commit()
                }
                R.id.actionFavorites -> {
                    startActivity(Intent(this, FavoriteActivity::class.java))
                }
                R.id.actionPopular -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            activityMainBinding.fcvSection.id,
                            PopularFragment()
                        )
                        .commit()
                }
                R.id.actionTopRated -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            activityMainBinding.fcvSection.id,
                            TopRatedFragment()
                        )
                        .commit()
                }
                R.id.actionUpcoming -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            activityMainBinding.fcvSection.id,
                            UpComingFragment()
                        )
                        .commit()
                }
                R.id.actionPosters -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            activityMainBinding.fcvSection.id,
                            PosterFragment()
                        )
                        .commit()
                }
            }
            activityMainBinding.drawerHome.closeDrawers()
            false
        }

        activityMainBinding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.actionTopRated -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            activityMainBinding.fcvSection.id,
                            TopRatedFragment()
                        )
                        .commit()
                }
                R.id.actionUpcoming -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            activityMainBinding.fcvSection.id,
                            UpComingFragment()
                        )
                        .commit()
                }
                R.id.actionDiscover -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            activityMainBinding.fcvSection.id,
                            DiscoverFragment()
                        )
                        .commit()
                }
            }
            false
        }
        activityMainBinding.btnFavorite.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
            //activityMainBinding.drawerHome.openDrawer(Gravity.RIGHT)
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, Bundle().apply {
                putString(FirebaseAnalytics.Param.ITEM_ID, "12")
                putString(FirebaseAnalytics.Param.ITEM_NAME, "favorite")
                putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image")

            })


        }

        if (intent.hasExtra("body")) {
            activityMainBinding.btnFavorite.text = intent.getStringExtra("body")
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(
            receiver(),
            IntentFilter("Notification_Action")
        )

    }

    private fun receiver() = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if(intent?.hasExtra("bodyAction") == true) {
                Toast.makeText(this@MainActivity, intent.getStringExtra("bodyAction"),
                    Toast.LENGTH_SHORT).show()
                //activityMainBinding.btnFavorite.text = intent.getStringExtra("bodyAction")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver())
    }

}