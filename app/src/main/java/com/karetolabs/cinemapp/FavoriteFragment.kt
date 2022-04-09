package com.karetolabs.cinemapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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
        val favoriteAdapter = FavoriteAdapter(FavoriteProvider.favorites)
        favoriteAdapter.onItemClickListener = object: OnItemClickListener<Favorite>{
            override fun onItemSelected(item: Favorite) {
                favoriteFragmentListener?.addDetailEvent(item)
            }
        }
        //LinearLayoutManager(requireActivity())
        //LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        //LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, true)
        //LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, true)
        //StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        //StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
        //GridLayoutManager(requireActivity(), 2, GridLayoutManager.VERTICAL, false)
        //GridLayoutManager(requireActivity(), 2, GridLayoutManager.VERTICAL, true)
        fragmentFavoriteBinding.rvFavorites.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2, GridLayoutManager.HORIZONTAL, false)
            adapter = favoriteAdapter
        }
    }

    interface FavoriteFragmentListener {
        fun addFavoriteEvent()
        fun addDetailEvent(favorite: Favorite)
    }


}