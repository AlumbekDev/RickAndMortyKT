package com.example.rickandmortykt.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortykt.data.network.dto.episode.EpisodeDto

@Dao
interface EpisodeDao {

    @Query("SELECT * FROM EpisodeDto")
    fun getAllEpisodes(): LiveData<List<EpisodeDto>>

    @Query("SELECT * FROM episodedto WHERE id = :id")
    fun getEpisode(id: Int): LiveData<EpisodeDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<EpisodeDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: EpisodeDto)
}