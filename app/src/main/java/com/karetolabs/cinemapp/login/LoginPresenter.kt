package com.karetolabs.cinemapp.login

import com.karetolabs.cinemapp.login.data.USER_NAME_PREFERENCE
import com.karetolabs.cinemapp.login.data.UserPreferences

class LoginPresenter(
    private val loginView: LoginContract.LoginView,
    private val userPreferences: UserPreferences
) : LoginContract.LoginPresenter {


    init {
        if(userPreferences.isUserLogin()){
            loginView.onSuccessLogin("Bienvenido ${userPreferences.getStringPreference(USER_NAME_PREFERENCE)}")
        }
    }

    override fun login(email: String, password: String) {
        loginView.showLoading()
        if (email.isEmpty()) {
            loginView.hideLoading()
            loginView.onErrorLogin("El campo de email es requerido")
            return
        }
        loginView.hideLoading()
        userPreferences.setUserLoginSuccess(true)
        userPreferences.setUserNameSUccess(email)
        loginView.onSuccessLogin("Bienvenido $email")
    }
}