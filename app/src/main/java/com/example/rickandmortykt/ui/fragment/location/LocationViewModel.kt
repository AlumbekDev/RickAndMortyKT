package com.example.rickandmortykt.ui.fragment.location

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortykt.common.base.BaseViewModel
import com.example.rickandmortykt.data.network.dto.location.LocationDto
import com.example.rickandmortykt.data.repository.LocationRepository
import kotlinx.coroutines.launch

class LocationViewModel constructor(
    private val repository: LocationRepository
) : BaseViewModel() {

    fun fetchLocations() = repository.fetchLocations().cachedIn(viewModelScope)

    fun getLocations() = repository.getLocations()

    fun insertAllLocations(list: List<LocationDto>) = viewModelScope.launch {
        repository.insertAllLocations(list)
    }
}