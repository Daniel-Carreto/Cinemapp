package com.karetolabs.cinemapp.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.karetolabs.cinemapp.R
import com.karetolabs.cinemapp.databinding.FragmentPopularBinding

class PopularFragment : Fragment() {

    private lateinit var popularBinding: FragmentPopularBinding
    private var popularViewModel: PopularViewModel? = null
    //private var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        popularBinding = FragmentPopularBinding.inflate(layoutInflater,container, false)
        return popularBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularViewModel = ViewModelProvider(
            this,
            PopularViewModelFactory(5)
        )[PopularViewModel::class.java]

        popularViewModel?.contadorLiveData?.observe(requireActivity()){
            popularBinding.tvContador.text = it.toString()
        }

        //popularBinding.tvContador.text = ((popularViewModel?.inicializador?: 0) + (popularViewModel?.contador?:0)).toString()//.toString()///contador.toString()
        popularBinding.tvContador.setOnClickListener {
           // contador++
            popularViewModel?.actualizarContador()
            //popularBinding.tvContador.text = ((popularViewModel?.inicializador?: 0) + (popularViewModel?.contador?:0)).toString()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = PopularFragment()
    }
}