package com.karetolabs.cinemapp.dulceria.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.karetolabs.cinemapp.R
import com.karetolabs.cinemapp.databinding.FragmentAddressBinding


class AddressFragment : Fragment() {


    private lateinit var addressBinding: FragmentAddressBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addressBinding = FragmentAddressBinding.inflate(layoutInflater, container, false)
        return addressBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addressBinding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_addressFragment_to_mapsFragment)
        }
        addressBinding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddressFragment()
    }
}