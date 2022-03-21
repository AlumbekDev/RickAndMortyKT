package com.example.rickandmortykt.data.network.paginsources

import com.example.rickandmortykt.common.base.BasePagingSource
import com.example.rickandmortykt.data.network.api.EpisodeApiService
import com.example.rickandmortykt.data.network.dto.episode.EpisodeDto

class EpisodePagingSource(
    private val service: EpisodeApiService
) : BasePagingSource<EpisodeDto>({ position ->
    service.fetchEpisodes(position)
})