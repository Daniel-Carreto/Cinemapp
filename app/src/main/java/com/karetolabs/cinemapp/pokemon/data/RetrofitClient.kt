package com.karetolabs.cinemapp.pokemon.data

import android.util.Log
import com.karetolabs.cinemapp.data.network.NetworkConnection
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class RetrofitClient: NetworkProvider<Retrofit> {

    override fun getBaseUrl() = "https://pokeapi.co/api/v2/"

    override fun getClient(): Retrofit {
       return Retrofit.Builder()
           .baseUrl(getBaseUrl())
           .client(createLoggingClient())
           .addConverterFactory(GsonConverterFactory.create())
           .build()
    }

    private fun createLoggingClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(
            HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.d("TAG_NETWORK", message)
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        okHttpClientBuilder.connectionSpecs(arrayListOf(ConnectionSpec.COMPATIBLE_TLS))
        okHttpClientBuilder.connectTimeout(100, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(100, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(100, TimeUnit.SECONDS)
        return okHttpClientBuilder.build()
    }

    fun <T> provideApi(service: Class<T>): T {
        return getClient().create(service)
    }



}