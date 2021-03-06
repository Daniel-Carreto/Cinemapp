package com.karetolabs.cinemapp

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karetolabs.cinemapp.databinding.ItemFavoriteBinding

class FavoriteAdapter(val favorites: List<Favorite>) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    var onItemClickListener: OnItemClickListener<Favorite>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding =
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favorite = favorites[position]
        holder.binding.tvTitle.text = favorite.title
        holder.binding.tvSummary.text = favorite.summary
        holder.binding.tvYear.text = favorite.year
        if(favorite.uriImage?.isNotEmpty() == true){
            holder.binding.ivPoster.setImageURI(Uri.parse(favorite.uriImage))
        }else {
            Glide.with(holder.binding.root.context).load(favorite.urlImage)
                .placeholder(ContextCompat.getDrawable(holder.binding.ivPoster.context,R.drawable.ic_image))
                .into(holder.binding.ivPoster)
        }
        holder.binding.root.setOnClickListener {
            onItemClickListener?.onItemSelected(favorite)
        }
    }

    override fun getItemCount() = favorites.size

    override fun getItemId(position: Int): Long {
        return favorites[position].id ?: super.getItemId(position)
    }

    class FavoriteViewHolder(val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root)
}