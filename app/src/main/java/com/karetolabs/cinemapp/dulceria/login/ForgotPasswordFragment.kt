package com.karetolabs.cinemapp.dulceria.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.karetolabs.cinemapp.R
import com.karetolabs.cinemapp.databinding.FragmentForgotPasswordBinding


class ForgotPasswordFragment : BottomSheetDialogFragment() {

    private lateinit var forgotPasswordBinding: FragmentForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        forgotPasswordBinding = FragmentForgotPasswordBinding.inflate(layoutInflater, container, false)
        return forgotPasswordBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ForgotPasswordFragment()
    }
}