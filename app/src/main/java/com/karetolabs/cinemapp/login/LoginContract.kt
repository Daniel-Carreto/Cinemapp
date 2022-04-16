package com.karetolabs.cinemapp.login

interface LoginContract {
    interface LoginView {
        fun showLoading()
        fun hideLoading()
        fun onSuccessLogin(message: String)
        fun onErrorLogin(message: String)
    }

    interface LoginPresenter {
        fun login(email: String, password: String)
    }
}