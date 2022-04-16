package com.karetolabs.cinemapp.login.data

interface UserPreferences {
    fun setUserLoginSuccess(status: Boolean)
    fun setUserNameSUccess(username: String)
    fun isUserLogin(): Boolean
    fun clearUserSession()
}