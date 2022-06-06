package com.karetolabs.cinemapp.dulceria.home

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karetolabs.cinemapp.Favorite
import com.karetolabs.cinemapp.OnItemClickListener
import com.karetolabs.cinemapp.R
import com.karetolabs.cinemapp.databinding.ItemCandyHeaderBinding
import com.karetolabs.cinemapp.databinding.ItemFavoriteBinding

const val HEADER_VIEW_TYPE = 1

class FavoriteCandyAdapter(val favorites: List<Favorite>, val encabezado: String) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClickListener: OnItemClickListener<Favorite>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == HEADER_VIEW_TYPE){
            val binding =
                ItemCandyHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            HeaderViewHolder(binding)
        } else {
            val binding =
                ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            FavoriteViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FavoriteViewHolder) {
            val favorite = favorites[position]
            holder.binding.tvTitle.text = favorite.title
            holder.binding.tvSummary.text = favorite.summary
            holder.binding.tvYear.text = favorite.year
            if (favorite.uriImage?.isNotEmpty() == true) {
                holder.binding.ivPoster.setImageURI(Uri.parse(favorite.uriImage))
            } else {
                Glide.with(holder.binding.root.context).load(favorite.urlImage)
                    .placeholder(
                        ContextCompat.getDrawable(
                            holder.binding.ivPoster.context,
                            R.drawable.ic_image
                        )
                    )
                    .into(holder.binding.ivPoster)
            }
            holder.binding.root.setOnClickListener {
                onItemClickListener?.onItemSelected(favorite)
            }
            //headers,adview,footer
        } else if (holder is HeaderViewHolder){
            holder.binding.textView3.text = encabezado
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position  == 0) {
            HEADER_VIEW_TYPE
        } else {
            super.getItemViewType(position)
        }
    }

    override fun getItemCount() = favorites.size

    override fun getItemId(position: Int): Long {
        return favorites[position].id ?: super.getItemId(position)
    }

    class FavoriteViewHolder(val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root)


    class HeaderViewHolder(val binding: ItemCandyHeaderBinding) :
        RecyclerView.ViewHolder(binding.root)
}