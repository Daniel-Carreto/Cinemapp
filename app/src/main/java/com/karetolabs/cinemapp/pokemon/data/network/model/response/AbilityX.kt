package com.karetolabs.cinemapp.pokemon.data.network.model.response


import com.google.gson.annotations.SerializedName

data class AbilityX(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)