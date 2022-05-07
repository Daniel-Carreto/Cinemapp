package com.karetolabs.cinemapp.poster

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.karetolabs.cinemapp.R
import com.karetolabs.cinemapp.databinding.FragmentPosterBinding


class PosterFragment : Fragment() {

    private lateinit var fragmentPosterBinding: FragmentPosterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentPosterBinding = FragmentPosterBinding.inflate(layoutInflater, container, false)
        return fragmentPosterBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val posterImages = arrayListOf(
            "https://images-eu.ssl-images-amazon.com/images/I/5117ZW5600L.__AC_SX300_SY300_QL70_ML2_.jpg",
            "https://c8.alamy.com/compes/2c4x052/inception-2010-dirigida-por-christopher-nolan-y-protagonizada-por-leonardo-dicaprio-joseph-gordon-levitt-ellen-page-tom-hardy-y-ken-watanabe-un-equipo-irnante-al-subconsciente-de-un-hombre-de-negocios-utilizando-la-tecnologia-de-compartir-suenos-para-una-planta-una-semilla-para-influir-en-su-decision-en-el-mundo-real-2c4x052.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9lqKg-B14vhvfGuuy6Rj5NH7yrtGWiR2WvQ&usqp=CAU"
        )
        val posterAdapter = PosterAdapter(
            requireActivity(),
            posterImages
        )
        fragmentPosterBinding.vpPoster.adapter = posterAdapter
        fragmentPosterBinding.vpPoster.addOnPageChangeListener(object :
            ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                //Log.d("PageViewPager", "Postion:$position::PositionOffset:$positionOffset:: PositionPixels:$positionOffsetPixels ")
            }

            override fun onPageSelected(position: Int) {
                Log.d("PageViewPager", "Postion:${posterImages[position]}:::")

            }

            override fun onPageScrollStateChanged(state: Int) {
                // Log.d("PageViewPager", "Postion:$state:::")
            }

        })
        fragmentPosterBinding.tabLayout.setupWithViewPager(fragmentPosterBinding.vpPoster)

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PosterFragment().apply {

            }
    }
}