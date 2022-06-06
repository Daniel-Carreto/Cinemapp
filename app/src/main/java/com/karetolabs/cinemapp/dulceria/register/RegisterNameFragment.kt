package com.karetolabs.cinemapp.dulceria.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.karetolabs.cinemapp.R
import com.karetolabs.cinemapp.databinding.FragmentRegisterNameBinding


class RegisterNameFragment : Fragment() {

    private lateinit var registerNameBinding: FragmentRegisterNameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        registerNameBinding = FragmentRegisterNameBinding.inflate(layoutInflater, container, false)
        return registerNameBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerNameBinding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }

        registerNameBinding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.addressFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = RegisterNameFragment()
    }
}