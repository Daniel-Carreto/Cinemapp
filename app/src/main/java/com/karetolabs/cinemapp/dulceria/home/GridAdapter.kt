package com.karetolabs.cinemapp.dulceria.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.karetolabs.cinemapp.R
import com.karetolabs.cinemapp.databinding.GridItemBinding
import com.karetolabs.cinemapp.util.loadUrl

class GridAdapter(context: Context, list: List<GridModel>)
    : ArrayAdapter<GridModel>(context, 0, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var gridItemView = convertView
        if(gridItemView == null){
            gridItemView = GridItemBinding.inflate(LayoutInflater.from(context)).root
        }
        val gridItem : GridModel? = getItem(position)
        gridItemView.findViewById<TextView>(R.id.tvTitle).text = gridItem?.name
        gridItemView.findViewById<ImageView>(R.id.idImageview).loadUrl(gridItem?.image.orEmpty())
        return gridItemView
    }
}

data class GridModel(
    var id: Long,
    var name: String,
    var image: String?
)