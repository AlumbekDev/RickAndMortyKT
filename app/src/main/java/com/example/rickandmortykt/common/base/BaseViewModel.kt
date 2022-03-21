package com.example.rickandmortykt.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortykt.common.resource.Resource
import com.example.rickandmortykt.ui.state.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    protected fun <T> MutableStateFlow<UIState<T>>.subscribeTo(
        request: () -> Flow<Resource<T>>,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            request().collect {
                when (it) {
                    is Resource.Loading -> {
                        this@subscribeTo.value = UIState.Loading()
                    }
                    is Resource.Error -> it.message?.let { error ->
                        this@subscribeTo.value = UIState.Error(error)
                    }
                    is Resource.Success -> it.data?.let { data ->
                        this@subscribeTo.value = UIState.Success(data)
                    }
                }
            }
        }
    }
}
