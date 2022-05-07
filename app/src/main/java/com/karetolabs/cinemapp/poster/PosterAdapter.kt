package com.karetolabs.cinemapp.poster

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.karetolabs.cinemapp.databinding.ItemPosterBinding

class PosterAdapter(): PagerAdapter() {

    var posters: List<String>? = null
    private lateinit var layoutInflater: LayoutInflater

    //Constructor secundario
    constructor(context: Context): this(){

    }

    constructor(context: Context, posters:List<String>): this(context){
        this.posters = posters
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int = posters?.size ?: 0

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = ItemPosterBinding.inflate(layoutInflater, container, false)
        Glide.with(itemView.root.context).load(posters?.get(position)).into(itemView.ivPoster)
        container.addView(itemView.root)
        return itemView.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}