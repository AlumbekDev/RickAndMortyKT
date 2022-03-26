package com.example.rickandmortykt.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickandmortykt.common.base.BaseRepository
import com.example.rickandmortykt.data.network.api.CharacterApiService
import com.example.rickandmortykt.data.network.dto.character.CharacterDto
import com.example.rickandmortykt.data.network.paginsources.CharacterPagingSource
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val service: CharacterApiService,
) : BaseRepository() {

    fun fetchCharacters(): LiveData<PagingData<CharacterDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = {
                CharacterPagingSource(service)
            }
        ).liveData
    }

    fun fetchCharacter(id: Int) = doRequest {
        service.fetchCharacter(id)
    }
}