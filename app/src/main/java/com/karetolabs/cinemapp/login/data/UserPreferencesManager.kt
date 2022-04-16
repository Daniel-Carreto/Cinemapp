package com.karetolabs.cinemapp.login.data

import android.content.SharedPreferences

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
        sharedPreferences?.edit()?.putString("userName", username)?.apply()
    }

    override fun isUserLogin(): Boolean {
        return sharedPreferences?.getBoolean("user_login", false) ?: false
    }

    override fun clearUserSession() {
        sharedPreferences?.edit()?.clear()?.apply()
    }
}