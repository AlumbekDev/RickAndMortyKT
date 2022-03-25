package com.example.rickandmortykt.ui.fragment.episode

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortykt.common.base.BaseViewModel
import com.example.rickandmortykt.data.repository.EpisodeRepository

class EpisodeViewModel constructor(
    private val repository: EpisodeRepository
) : BaseViewModel() {

    fun fetchEpisode() = repository.fetchEpisodes().cachedIn(viewModelScope)
}