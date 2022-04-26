package com.example.rickandmortykt.ui.fragment.location.locationsDetail

import com.example.rickandmortykt.common.base.BaseViewModel
import com.example.rickandmortykt.data.network.dto.location.LocationDto
import com.example.rickandmortykt.data.repository.LocationRepository
import com.example.rickandmortykt.ui.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LocationsDetailViewModel constructor(

    private val repository: LocationRepository
) : BaseViewModel() {
    private val _locationState = MutableStateFlow<UIState<LocationDto>>(UIState.Loading())
    var locationState: StateFlow<UIState<LocationDto>> = _locationState

    fun fetchLocation(id: Int) {
        _locationState.subscribeTo {
            repository.fetchLocation(id)
        }
    }
}