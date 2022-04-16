package com.karetolabs.cinemapp.login.data

import android.content.Context
import android.content.SharedPreferences

object PreferencesProvider {

    private var sharedPreferences: SharedPreferences? = null

    fun providePreferences(): SharedPreferences? {
        return sharedPreferences
    }

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("Login", Context.MODE_PRIVATE)
    }


}