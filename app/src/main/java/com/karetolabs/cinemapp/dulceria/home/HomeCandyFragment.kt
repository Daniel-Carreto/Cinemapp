package com.karetolabs.cinemapp.dulceria.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.karetolabs.cinemapp.R


class HomeCandyFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home_candy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.tvHome).setOnClickListener {

            val checkoutItem = 7
            val action = HomeCandyFragmentDirections.actionHomeCandyFragmentToBagFragment(checkoutItem)
            //findNavController().navigate(R.id.action_homeCandyFragment_to_bagFragment)
            findNavController().navigate(action)
        }

        view.findViewById<TextView>(R.id.tvSettings).setOnClickListener {
            findNavController().navigate(R.id.action_homeCandyFragment_to_settingsFragment)
        }

//        view.findViewById<TextView>(R.id.tvHome).setOnClickListener {
//           findNavController().navigate(R.id.action_homeCandyFragment_to_favoriteCandyFragment)
//        }
    }
}