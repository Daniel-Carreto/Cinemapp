package com.karetolabs.cinemapp.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit

object NetworkConnection {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    fun provideRetrofit(baseUrl: String? = BASE_URL): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        // loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        // loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


        val okhttp = OkHttpClient.Builder()
            //.connectTimeout(Duration.ofMillis(10_000))
            .connectTimeout(45, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okhttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    fun <T> provideApi(service: Class<T>): T? {
        return provideRetrofit().create(service)
    }

}