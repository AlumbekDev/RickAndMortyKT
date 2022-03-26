package com.example.rickandmortykt.ui.fragment.episode

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortykt.common.base.BaseViewModel
import com.example.rickandmortykt.data.repository.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val repository: EpisodeRepository
) : BaseViewModel() {

    fun fetchEpisode() = repository.fetchEpisodes().cachedIn(viewModelScope)
}