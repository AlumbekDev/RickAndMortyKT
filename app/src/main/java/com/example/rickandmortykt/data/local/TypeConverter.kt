package com.example.rickandmortykt.data.local

import androidx.room.TypeConverter
import com.example.rickandmortykt.data.network.dto.character.OriginDto
import com.example.rickandmortykt.data.network.dto.character.SimpleLocationDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {

    @TypeConverter
    fun fromOrigin(origin: OriginDto): String {
        return Gson().toJson(origin)
    }

    @TypeConverter
    fun fromStringToOrigin(string: String): OriginDto {
        val origin = object :
            TypeToken<OriginDto>() {}.type
        return Gson().fromJson(string, origin)
    }

    @TypeConverter
    fun fromLocation(location: SimpleLocationDto): String {
        return Gson().toJson(location)
    }

    @TypeConverter
    fun fromStringToLocation(string: String): SimpleLocationDto {
        val location = object :
            TypeToken<SimpleLocationDto>() {}.type
        return Gson().fromJson(string, location)
    }

    @TypeConverter
    fun fromMutableList(episode: List<String>): String {
        return Gson().toJson(episode)
    }

    @TypeConverter
    fun fromStringToList(string: String): List<String> {
        val episode = object :
            TypeToken<List<String>>() {}.type
        return Gson().fromJson(string, episode)
    }
}