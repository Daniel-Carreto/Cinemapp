package com.karetolabs.cinemapp.dulceria.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karetolabs.cinemapp.R
import com.karetolabs.cinemapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var settingsBinding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        settingsBinding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return settingsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val courseModelArrayList: ArrayList<GridModel> = ArrayList<GridModel>()
        courseModelArrayList.add(GridModel(0,"DSA", "https://images-eu.ssl-images-amazon.com/images/I/5117ZW5600L.__AC_SX300_SY300_QL70_ML2_.jpg"))
        courseModelArrayList.add(GridModel(0,"JAVA", "https://images-eu.ssl-images-amazon.com/images/I/5117ZW5600L.__AC_SX300_SY300_QL70_ML2_.jpg"))
        courseModelArrayList.add(GridModel(0,"C++", "https://images-eu.ssl-images-amazon.com/images/I/5117ZW5600L.__AC_SX300_SY300_QL70_ML2_.jpg"))
        courseModelArrayList.add(GridModel(0,"Python","https://images-eu.ssl-images-amazon.com/images/I/5117ZW5600L.__AC_SX300_SY300_QL70_ML2_.jpg"))
        courseModelArrayList.add(GridModel(0,"DSA", "https://images-eu.ssl-images-amazon.com/images/I/5117ZW5600L.__AC_SX300_SY300_QL70_ML2_.jpg"))
        courseModelArrayList.add(GridModel(0,"JAVA", "https://images-eu.ssl-images-amazon.com/images/I/5117ZW5600L.__AC_SX300_SY300_QL70_ML2_.jpg"))
        courseModelArrayList.add(GridModel(0,"C++", "https://images-eu.ssl-images-amazon.com/images/I/5117ZW5600L.__AC_SX300_SY300_QL70_ML2_.jpg"))
        courseModelArrayList.add(GridModel(0,"Python","https://images-eu.ssl-images-amazon.com/images/I/5117ZW5600L.__AC_SX300_SY300_QL70_ML2_.jpg"))
        courseModelArrayList.add(GridModel(0,"DSA", "https://images-eu.ssl-images-amazon.com/images/I/5117ZW5600L.__AC_SX300_SY300_QL70_ML2_.jpg"))
        courseModelArrayList.add(GridModel(0,"JAVA", "https://images-eu.ssl-images-amazon.com/images/I/5117ZW5600L.__AC_SX300_SY300_QL70_ML2_.jpg"))
        courseModelArrayList.add(GridModel(0,"C++", "https://images-eu.ssl-images-amazon.com/images/I/5117ZW5600L.__AC_SX300_SY300_QL70_ML2_.jpg"))
        courseModelArrayList.add(GridModel(0,"Python","https://images-eu.ssl-images-amazon.com/images/I/5117ZW5600L.__AC_SX300_SY300_QL70_ML2_.jpg"))
        val gridModelAdapter = GridAdapter(requireActivity(), courseModelArrayList)
        settingsBinding.gridSettings.adapter = gridModelAdapter

    }
}