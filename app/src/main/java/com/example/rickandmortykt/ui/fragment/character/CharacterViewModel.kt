package com.example.rickandmortykt.ui.fragment.character

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortykt.common.base.BaseViewModel
import com.example.rickandmortykt.data.repository.CharacterRepository

class CharacterViewModel constructor(
    private val repository: CharacterRepository
) : BaseViewModel() {
    fun fetchCharacters() = repository.fetchCharacters().cachedIn(viewModelScope)
}