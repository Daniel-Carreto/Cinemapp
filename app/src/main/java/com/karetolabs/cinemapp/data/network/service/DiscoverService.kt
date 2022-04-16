package com.karetolabs.cinemapp.data.network.service

import com.karetolabs.cinemapp.data.network.response.DiscoverResponse
import com.karetolabs.cinemapp.data.network.response.MovieResponse
import retrofit2.http.*

interface DiscoverService {

    //https:www.dominio.com/ --baseUrl
//    @Headers("content-type: application/json")
//    @GET("users/{user}/repos")
//    suspend fun discoverHeaders(@Path("user") user: String): DiscoverResponse
//
//
//    @Headers("content-type: application/json")
//    @POST("users/repos")
//    suspend fun discoverListPost(
//        @HeaderMap headers: Map<String, String>,
//        @QueryMap querymap: Map<String, String>,
//        @Body bodyRequest: MovieResponse
//    ): DiscoverResponse

    @GET
    suspend fun discoverList(@Url url: String): DiscoverResponse


}