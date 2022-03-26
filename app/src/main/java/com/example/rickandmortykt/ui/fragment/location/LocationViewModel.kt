package com.example.rickandmortykt.ui.fragment.location

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortykt.common.base.BaseViewModel
import com.example.rickandmortykt.data.repository.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repository: LocationRepository
) : BaseViewModel() {

    fun fetchLocations() = repository.fetchLocations().cachedIn(viewModelScope)
}