package com.karetolabs.cinemapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karetolabs.cinemapp.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private lateinit var fragmentFavoriteBinding: FragmentFavoriteBinding

    var favoriteFragmentListener: FavoriteFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FavoriteActivity) {
            favoriteFragmentListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentFavoriteBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentFavoriteBinding.fabAddFavorite.setOnClickListener {
            favoriteFragmentListener?.addFavoriteEvent()
        }
    }

    interface FavoriteFragmentListener {
        fun addFavoriteEvent()
    }


}