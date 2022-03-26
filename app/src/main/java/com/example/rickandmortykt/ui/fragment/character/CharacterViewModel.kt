package com.example.rickandmortykt.ui.fragment.character

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortykt.common.base.BaseViewModel
import com.example.rickandmortykt.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
) : BaseViewModel() {

    fun fetchCharacters() = repository.fetchCharacters().cachedIn(viewModelScope)
}