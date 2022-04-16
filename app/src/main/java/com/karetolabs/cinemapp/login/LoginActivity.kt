package com.karetolabs.cinemapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.karetolabs.cinemapp.MainActivity
import com.karetolabs.cinemapp.databinding.ActivityLoginBinding
import com.karetolabs.cinemapp.login.data.PreferencesProvider
import com.karetolabs.cinemapp.login.data.UserPreferencesManager

class LoginActivity : AppCompatActivity(), LoginContract.LoginView {

    private lateinit var loginBinding: ActivityLoginBinding
    private var presenter: LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        PreferencesProvider.init(this)
        presenter = LoginPresenter(
            this,
            UserPreferencesManager()
        )
        loginBinding.btnLogin.setOnClickListener {
            presenter?.login(
                loginBinding.tietUser.text.toString(),
                loginBinding.tietPassword.text.toString()
            )
        }
    }

    override fun showLoading() {
        loginBinding.progressLogin.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loginBinding.progressLogin.visibility = View.GONE
    }

    override fun onSuccessLogin(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onErrorLogin(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}