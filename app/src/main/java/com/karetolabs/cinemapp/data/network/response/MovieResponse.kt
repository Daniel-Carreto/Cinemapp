package com.karetolabs.cinemapp.data.network.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int?>?,
    val id: Long?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String?,
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?
)
