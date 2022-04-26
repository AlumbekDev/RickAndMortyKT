package com.example.rickandmortykt.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmortykt.data.local.daos.CharacterDao
import com.example.rickandmortykt.data.local.daos.EpisodeDao
import com.example.rickandmortykt.data.local.daos.LocationDao
import com.example.rickandmortykt.data.network.dto.character.CharacterDto
import com.example.rickandmortykt.data.network.dto.episode.EpisodeDto
import com.example.rickandmortykt.data.network.dto.location.LocationDto

@Database(
    entities = [CharacterDto::class, EpisodeDto::class, LocationDto::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun locationDao(): LocationDao
}