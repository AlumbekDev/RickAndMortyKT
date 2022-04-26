package com.example.rickandmortykt.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortykt.data.network.dto.location.LocationDto

@Dao
interface LocationDao {

    @Query("SELECT * FROM LocationDto")
    fun getAllLocations(): LiveData<List<LocationDto>>

    @Query("SELECT * FROM locationdto WHERE id = :id")
    fun getLocation(id: Int): LiveData<LocationDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<LocationDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: LocationDto)


}