package com.karetolabs.cinemapp.login.data

import android.content.SharedPreferences

const val USER_NAME_PREFERENCE = "userName"

class UserPreferencesManager : UserPreferences {

    var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences =
            PreferencesProvider.providePreferences()
    }

    override fun setUserLoginSuccess(status: Boolean) {
        sharedPreferences?.edit()?.putBoolean("user_login", status)?.apply()
    }

    override fun setUserNameSUccess(username: String) {
        sharedPreferences?.edit()?.putString(USER_NAME_PREFERENCE, username)?.apply()
    }

    override fun isUserLogin(): Boolean {
        return sharedPreferences?.getBoolean("user_login", false) ?: false
    }

    override fun getStringPreference(key: String): String {
        return sharedPreferences?.getString(key,"").orEmpty()
    }

    override fun clearUserSession() {
        sharedPreferences?.edit()?.clear()?.apply()
    }
}