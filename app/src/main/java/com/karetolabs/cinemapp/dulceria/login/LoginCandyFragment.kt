package com.karetolabs.cinemapp.dulceria.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.karetolabs.cinemapp.R
import com.karetolabs.cinemapp.databinding.FragmentLoginBinding

const val USERNAME_ARGS = "userName"
const val USER_ID_ARGS = "userId"
class LoginCandyFragment : Fragment() {

    private lateinit var loginBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBinding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return loginBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginBinding.btnLogin.setOnClickListener {
            val bundle = bundleOf(Pair(USER_ID_ARGS, 23), Pair(USERNAME_ARGS, loginBinding.editTextTextName.text))
            findNavController().navigate(R.id.action_loginCandyFragment_to_welcomeFragment, bundle)
        }

        loginBinding.btnRegister.setOnClickListener {
         findNavController().navigate(R.id.action_loginCandyFragment_to_registerNameFragment)
        }

        loginBinding.btnForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginCandyFragment_to_forgotPasswordFragment)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginCandyFragment()
    }
}