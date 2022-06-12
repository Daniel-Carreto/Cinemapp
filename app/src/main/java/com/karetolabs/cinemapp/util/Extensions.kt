package com.karetolabs.cinemapp.util

import android.content.Context
import android.util.Patterns
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide


fun ImageView.loadUrl(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun String.isEmailValid(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun Context.showAlert(mensaje: String?) {
    Toast.makeText(this, mensaje.orEmpty(), Toast.LENGTH_SHORT).show()
}

fun EditText.validarRegex(regex:String): Boolean {
    return this.text.contains(regex)
}