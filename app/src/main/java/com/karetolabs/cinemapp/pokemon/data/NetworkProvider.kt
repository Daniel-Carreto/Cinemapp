package com.karetolabs.cinemapp.pokemon.data

interface NetworkProvider<T> {
    fun getBaseUrl(): String
    fun getClient(): T
}