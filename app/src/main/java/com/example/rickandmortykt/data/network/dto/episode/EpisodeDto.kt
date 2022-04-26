package com.example.rickandmortykt.data.network.dto.episode

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortykt.common.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

@Entity
data class EpisodeDto(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    override val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("air_date")
    val air_date: String,
    @SerializedName("episode")
    val episode: String,
    @SerializedName("characters")
    val characters: MutableList<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val created: String
) : IBaseDiffModel