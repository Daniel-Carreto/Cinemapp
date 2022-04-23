package com.karetolabs.cinemapp.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PopularViewModel(
    private val inicializador: Int = 0
) : ViewModel() {

    private val contadorMutableLiveData = MutableLiveData<Int>()
    val contadorLiveData: LiveData<Int> = contadorMutableLiveData

    var contador = 0

    init {
        contadorMutableLiveData.value = inicializador
    }


    fun actualizarContador() {
        contadorMutableLiveData.value = (inicializador + ++contador)
    }

}