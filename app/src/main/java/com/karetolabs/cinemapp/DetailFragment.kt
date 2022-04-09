package com.karetolabs.cinemapp

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.karetolabs.cinemapp.databinding.FragmentDetailBinding

private const val ARG_PARAM1 = "idFavorite"

class DetailFragment : Fragment() {
    private var param1: Long? = null

    private lateinit var detailBinding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getLong(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailBinding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailBinding.toolbarDetail.navigationIcon =
            ContextCompat.getDrawable(requireActivity(), R.drawable.ic_add)
        detailBinding.toolbarDetail.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        val favorite = FavoriteProvider.favorites[param1?.toInt() ?: 0]

        detailBinding.tvTitle.text = favorite.title
        detailBinding.tvSummary.text = favorite.summary


        if(favorite.uriImage?.isNotEmpty() == true){
            detailBinding.ivPosterDetail.setImageURI(Uri.parse(favorite.uriImage))
        }else {
            Glide.with(requireActivity()).load(favorite.urlImage).into(detailBinding.ivPosterDetail)
        }

    }


    companion object {
        @JvmStatic
        fun newInstance(param1: Long) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_PARAM1, param1)
                }
            }
    }
}