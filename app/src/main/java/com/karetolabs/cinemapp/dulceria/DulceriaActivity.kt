package com.karetolabs.cinemapp.dulceria

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.karetolabs.cinemapp.R

class DulceriaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dulceria)
    }

    companion object {
        fun launch(context: Context, vararg datos:Pair<String, Any>) {
            val intent  = Intent(context, DulceriaActivity::class.java)
            datos.forEach {
                intent.putExtra(it.first, it.second.toString())
            }
            context.startActivity(intent)
        }
    }
}