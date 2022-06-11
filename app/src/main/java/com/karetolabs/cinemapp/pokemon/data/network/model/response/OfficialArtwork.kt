package com.karetolabs.cinemapp.pokemon.data.network.model.response


import com.google.gson.annotations.SerializedName

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String?
)