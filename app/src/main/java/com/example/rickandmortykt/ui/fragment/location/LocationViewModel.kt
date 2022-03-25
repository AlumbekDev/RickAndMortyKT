package com.example.rickandmortykt.ui.fragment.location

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortykt.common.base.BaseViewModel
import com.example.rickandmortykt.data.repository.LocationRepository

class LocationViewModel constructor(
    private val repository: LocationRepository
) : BaseViewModel() {

    fun fetchLocations() = repository.fetchLocations().cachedIn(viewModelScope)
}