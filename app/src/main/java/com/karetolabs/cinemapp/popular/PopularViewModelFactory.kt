package com.karetolabs.cinemapp.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PopularViewModelFactory(private val inicializador: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PopularViewModel(inicializador) as T
    }


}