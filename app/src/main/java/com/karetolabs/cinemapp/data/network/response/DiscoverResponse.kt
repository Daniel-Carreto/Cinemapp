package com.karetolabs.cinemapp.data.network.response

import com.google.gson.annotations.SerializedName

data class DiscoverResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<MovieResponse>,
    @SerializedName("total_pages")
    val totalPages: Long?,
    @SerializedName("total_results")
    val totalResults: Long?
)
