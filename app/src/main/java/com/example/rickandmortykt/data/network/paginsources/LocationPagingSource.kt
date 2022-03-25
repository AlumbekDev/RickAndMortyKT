package com.example.rickandmortykt.data.network.paginsources

import com.example.rickandmortykt.common.base.BasePagingSource
import com.example.rickandmortykt.data.network.api.LocationApiService
import com.example.rickandmortykt.data.network.dto.location.LocationDto

class LocationPagingSource(
    private val service: LocationApiService
) : BasePagingSource<LocationDto>({ position ->
    service.fetchLocations(position)

})