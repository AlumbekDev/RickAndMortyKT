package com.example.rickandmortykt.data.network.api

import com.example.rickandmortykt.data.network.dto.RickAndMortyResponse
import com.example.rickandmortykt.data.network.dto.character.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiService {

    @GET("/api/character")
    suspend fun fetchCharacters(
        @Query("page") page: Int,
    ): RickAndMortyResponse<CharacterDto>


    @GET("/api/character/{id}")
    suspend fun fetchCharacter(
        @Path("id") id: Int
    ): CharacterDto
}