package com.karetolabs.cinemapp.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karetolabs.cinemapp.databinding.ItemFavoriteBinding
import com.karetolabs.cinemapp.util.isEmailValid
import com.karetolabs.cinemapp.util.loadUrl
import com.karetolabs.cinemapp.util.showAlert

class DiscoverAdapter(val movies: List<Movie>) :
    RecyclerView.Adapter<DiscoverAdapter.DiscoverViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
        val binding =
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiscoverViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.tvTitle.text = movie.title
        holder.binding.ivPoster.loadUrl("https://image.tmdb.org/t/p/w200" + movie.posterPath)
        holder.binding.tvYear.text = movie.date
        holder.binding.tvSummary.text = movie.summary

    }

    override fun getItemCount() = movies.size


    class DiscoverViewHolder(val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root)
}
