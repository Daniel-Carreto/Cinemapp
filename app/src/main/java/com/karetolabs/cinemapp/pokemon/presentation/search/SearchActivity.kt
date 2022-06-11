package com.karetolabs.cinemapp.pokemon.presentation.search

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.karetolabs.cinemapp.R
import com.karetolabs.cinemapp.util.loadUrl

class SearchActivity : AppCompatActivity() {

    private val viewModel: SearchViewModel? by lazy {
        ViewModelProvider(this)[SearchViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val item = findViewById<EditText>(R.id.editTextTextPersonName2)
        findViewById<Button>(R.id.button).setOnClickListener {
            findViewById<TextView>(R.id.textView14).text = item.text
            viewModel?.search(item.text.toString())
        }


        lifecycleScope.launchWhenCreated {
            viewModel?.result?.collect {
                println("Activity->$it")
                findViewById<ImageView>(R.id.imageView4).loadUrl(it)
            }
        }
    }

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, SearchActivity::class.java)
            context.startActivity(intent)
        }
    }
}