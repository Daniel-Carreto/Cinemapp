package com.karetolabs.cinemapp.login.data

interface UserPreferences {
    fun setUserLoginSuccess(status: Boolean)
    fun isUserLogin(): Boolean
    fun setUserNameSUccess(username: String)
    fun getStringPreference(key:String):String
    fun clearUserSession()
}