package com.karetolabs.cinemapp.login

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.karetolabs.cinemapp.BuildConfig
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
        createChannel()

        Firebase.messaging.subscribeToTopic("estrenos")
        Firebase.messaging.unsubscribeFromTopic("estrenos")

        Firebase.messaging.token.addOnSuccessListener() {
            println(it)
        }

        //Log.d("BASE_URL+", "++++++"+BuildConfig.BASE_URL)

    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notiicationChannel =
                NotificationChannel("cinemapp", "cinemapp", NotificationManager.IMPORTANCE_HIGH)
            notiicationChannel.enableLights(true)
            notiicationChannel.enableVibration(true)
            notiicationChannel.lightColor = Color.BLUE
            (getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager)?.createNotificationChannel(
                notiicationChannel
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
        finish()
    }

    override fun onErrorLogin(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}