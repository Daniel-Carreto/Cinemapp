package com.karetolabs.cinemapp.pokemon.data.network.service

import com.karetolabs.cinemapp.pokemon.data.network.model.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchService {
    @GET("pokemon/{name}")
    suspend fun search(@Path("name") pokemon:String): SearchResponse
}