package com.karetolabs.cinemapp.dulceria.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.karetolabs.cinemapp.R
import com.karetolabs.cinemapp.databinding.FragmentCreditCardBinding


class CreditCardFragment : Fragment() {

    private lateinit var creditCardBinding: FragmentCreditCardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       creditCardBinding = FragmentCreditCardBinding.inflate(layoutInflater, container, false)
        return creditCardBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        creditCardBinding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
        creditCardBinding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_creditCardFragment_to_loginCandyFragment4)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreditCardFragment()
    }
}