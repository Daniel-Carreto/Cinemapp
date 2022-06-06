package com.karetolabs.cinemapp.dulceria.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karetolabs.cinemapp.FavoriteProvider
import com.karetolabs.cinemapp.R


class FavoriteCandyFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            println(it.containsKey("id"))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_favorite_candy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.rvFavoritesCandy).apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = FavoriteCandyAdapter(FavoriteProvider.favorites, "Peliculas")
        }
    }

}