package com.example.rickandmortykt.data.network.dto.character

import com.google.gson.annotations.SerializedName

class SimpleLocationDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)