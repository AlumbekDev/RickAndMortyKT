package com.example.rickandmortykt.ui.fragment.episode

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortykt.common.base.BaseViewModel
import com.example.rickandmortykt.data.network.dto.episode.EpisodeDto
import com.example.rickandmortykt.data.repository.EpisodeRepository
import kotlinx.coroutines.launch

class EpisodeViewModel constructor(
    private val repository: EpisodeRepository
) : BaseViewModel() {

    fun fetchEpisode() = repository.fetchEpisodes().cachedIn(viewModelScope)

    fun getEpisodes() = repository.getEpisodes()

    fun insertAllEpisodes(list: List<EpisodeDto>) = viewModelScope.launch {
        repository.insertAllEpisodes(list)
    }
}