package com.example.rickandmortykt.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortykt.data.network.dto.character.CharacterDto

@Dao
interface CharacterDao {

    @Query("SELECT * FROM CharacterDto")
    fun getAllCharacters(): LiveData<List<CharacterDto>>

    @Query("SELECT * FROM characterdto WHERE id = :id")
    fun getCharacter(id: Int): LiveData<CharacterDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: CharacterDto)
}