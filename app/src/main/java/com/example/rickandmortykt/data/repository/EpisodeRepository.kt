package com.example.rickandmortykt.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickandmortykt.common.base.BaseRepository
import com.example.rickandmortykt.data.network.api.EpisodeApiService
import com.example.rickandmortykt.data.network.dto.episode.EpisodeDto
import com.example.rickandmortykt.data.network.paginsources.EpisodePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val service: EpisodeApiService,
) : BaseRepository() {

    fun fetchEpisodes(): LiveData<PagingData<EpisodeDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = {
                EpisodePagingSource(service)
            }
        ).liveData
    }

    fun fetchEpisode(id: Int) = doRequest {
        service.fetchEpisode(id)
    }
}