package com.karetolabs.cinemapp.pokemon.data.network.model.response


import com.google.gson.annotations.SerializedName

data class VersionDetail(
    @SerializedName("rarity")
    val rarity: Int?,
    @SerializedName("version")
    val version: VersionX?
)