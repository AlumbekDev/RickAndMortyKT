package com.example.rickandmortykt.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortykt.common.base.BaseRepository
import com.example.rickandmortykt.data.network.api.EpisodeApiService
import com.example.rickandmortykt.data.network.dto.episode.EpisodeDto
import com.example.rickandmortykt.data.network.paginsources.EpisodePagingSource
import kotlinx.coroutines.flow.Flow

class EpisodeRepository constructor(
    private val service: EpisodeApiService
) : BaseRepository() {

    fun fetchEpisodes(): Flow<PagingData<EpisodeDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = {
                EpisodePagingSource(service)
            }
        ).flow
    }
    fun fetchEpisode(id: Int) = doRequest {
        service.fetchEpisode(id)
    }
}