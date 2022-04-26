package com.example.rickandmortykt.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickandmortykt.common.base.BaseRepository
import com.example.rickandmortykt.data.local.daos.LocationDao
import com.example.rickandmortykt.data.network.api.LocationApiService
import com.example.rickandmortykt.data.network.dto.location.LocationDto
import com.example.rickandmortykt.data.network.paginsources.LocationPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocationRepository constructor(
    private val service: LocationApiService,
    private val dao: LocationDao
) : BaseRepository() {

    fun fetchLocations(): LiveData<PagingData<LocationDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = {
                LocationPagingSource(service)
            }
        ).liveData
    }

    fun fetchLocation(id: Int) = doRequest {
        service.fetchLocation(id)
    }

    fun getLocations(): LiveData<List<LocationDto>> {
        return dao.getAllLocations()
    }

    suspend fun insertAllLocations(list: List<LocationDto>) = withContext(Dispatchers.IO) {
        dao.insertAll(list)
    }
}