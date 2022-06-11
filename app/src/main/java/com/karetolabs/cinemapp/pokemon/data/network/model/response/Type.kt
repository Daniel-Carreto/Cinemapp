package com.karetolabs.cinemapp.pokemon.data.network.model.response


import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("slot")
    val slot: Int?,
    @SerializedName("type")
    val type: TypeX?
)