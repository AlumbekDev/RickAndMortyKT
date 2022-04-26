package com.example.rickandmortykt.data.network.dto.character

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortykt.common.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

@Entity
data class CharacterDto(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    override val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("origin")
    val origin: OriginDto,
    @SerializedName("location")
    val location: SimpleLocationDto,
    @SerializedName("image")
    val image: String,
    @SerializedName("episode")
    val episode: MutableList<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val created: String,
) : IBaseDiffModel