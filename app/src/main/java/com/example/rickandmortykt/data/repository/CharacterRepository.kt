package com.example.rickandmortykt.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickandmortykt.common.base.BaseRepository
import com.example.rickandmortykt.data.local.daos.CharacterDao
import com.example.rickandmortykt.data.network.api.CharacterApiService
import com.example.rickandmortykt.data.network.dto.character.CharacterDto
import com.example.rickandmortykt.data.network.paginsources.CharacterPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepository constructor(
    private val service: CharacterApiService,
    private val dao: CharacterDao
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

    fun getCharacters(): LiveData<List<CharacterDto>> {
        return dao.getAllCharacters()
    }

    suspend fun insertAllCharacters(list: List<CharacterDto>) = withContext(Dispatchers.IO) {
        dao.insertAll(list)
    }
}