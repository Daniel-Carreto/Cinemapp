package com.karetolabs.cinemapp.dulceria.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.MODE_FIXED
import com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE
import com.karetolabs.cinemapp.TopRated.TopRatedFragment
import com.karetolabs.cinemapp.databinding.FragmentBagBinding


class BagFragment : Fragment() {

    private lateinit var bagBinding: FragmentBagBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val safeArgs : BagFragmentArgs by navArgs()
        val checkoutValue = safeArgs.checkoutArg
        println("Valor en el Checkout $checkoutValue")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bagBinding = FragmentBagBinding.inflate(layoutInflater, container, false)
        return bagBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cupones = bagBinding.tabBag.newTab().setText("Cupones")
        bagBinding.tabBag.addTab(bagBinding.tabBag.newTab().setText("Bolsa"))
        bagBinding.tabBag.addTab(cupones)
        bagBinding.tabBag.addTab(bagBinding.tabBag.newTab().setText("Forma de Pago"))
        bagBinding.tabBag.addTab(bagBinding.tabBag.newTab().setText("Forma de Pago"))
        bagBinding.tabBag.tabMode = MODE_SCROLLABLE
        bagBinding.tabBag.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                println("onTabSelected"+ tab?.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                println("onTabUnselected")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                println("onTabREselected")
            }

        })
        bagBinding.tabBag.selectTab(cupones)

        childFragmentManager.beginTransaction()
            .replace(
                bagBinding.frlCandy.id,
                TopRatedFragment()
            )
            .commit()
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BagFragment()
    }
}