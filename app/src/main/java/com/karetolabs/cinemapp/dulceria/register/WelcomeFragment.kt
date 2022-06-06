package com.karetolabs.cinemapp.dulceria.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.karetolabs.cinemapp.R
import com.karetolabs.cinemapp.databinding.FragmentWelcomeBinding
import com.karetolabs.cinemapp.dulceria.login.USERNAME_ARGS
import com.karetolabs.cinemapp.dulceria.login.USER_ID_ARGS

class WelcomeFragment : Fragment() {

    private lateinit var welcomeBinding: FragmentWelcomeBinding

    private var userName = ""
    private var idUser = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            if(bundle.containsKey(USER_ID_ARGS)){
                idUser = bundle.getInt(USER_ID_ARGS)
            }
            if(bundle.containsKey(USERNAME_ARGS)){
                userName = bundle.get(USERNAME_ARGS).toString()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        welcomeBinding = FragmentWelcomeBinding.inflate(layoutInflater, container, false)
        return welcomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        welcomeBinding.tvWelcome.text = "Bienvenido $userName"
        welcomeBinding.tvWelcome.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_homeCandyFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WelcomeFragment()
    }
}