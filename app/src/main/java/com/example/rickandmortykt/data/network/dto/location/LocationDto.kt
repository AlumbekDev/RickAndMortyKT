package com.example.rickandmortykt.data.network.dto.location

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortykt.common.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

@Entity
data class LocationDto(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    override val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("dimension")
    val dimension: String,
    @SerializedName("residents")
    val residents: MutableList<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val created: String
) : IBaseDiffModel