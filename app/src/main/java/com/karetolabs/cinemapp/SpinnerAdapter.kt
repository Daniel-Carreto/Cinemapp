package com.karetolabs.cinemapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.karetolabs.cinemapp.databinding.ItemGeneroBinding

class SpinnerAdapter(
    context: Activity,
    id:Int,private val genres:List<Genero>
    ) : ArrayAdapter<Genero>(context,id, genres) {

    private val layoutInflater:LayoutInflater


    init {
        layoutInflater = context.layoutInflater
    }

    override fun getCount(): Int {
        return genres.size
    }

    override fun getItem(position: Int): Genero? {
        return genres[position]
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return rowGenre(position)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
       return rowGenre(position)
    }

    private fun rowGenre(position:Int): View {
        val genre = genres[position]
        val binding = ItemGeneroBinding.inflate(layoutInflater, null, false)
        binding.textView.text = genre.title
        return binding.root
    }

}