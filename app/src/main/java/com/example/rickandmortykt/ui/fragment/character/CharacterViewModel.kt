package com.example.rickandmortykt.ui.fragment.character

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortykt.common.base.BaseViewModel
import com.example.rickandmortykt.data.network.dto.character.CharacterDto
import com.example.rickandmortykt.data.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharacterViewModel constructor(
    private val repository: CharacterRepository
) : BaseViewModel() {

    fun fetchCharacters() = repository.fetchCharacters().cachedIn(viewModelScope)

    fun getCharacters() = repository.getCharacters()

    fun insertAllCharacters(list: List<CharacterDto>) = viewModelScope.launch {
        repository.insertAllCharacters(list)
    }
}