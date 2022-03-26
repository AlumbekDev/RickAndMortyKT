package com.example.rickandmortykt.ui.fragment.character.charactersDetail

import com.example.rickandmortykt.common.base.BaseViewModel
import com.example.rickandmortykt.data.network.dto.character.CharacterDto
import com.example.rickandmortykt.data.repository.CharacterRepository
import com.example.rickandmortykt.ui.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CharactersDetailViewModel @Inject constructor(
    private val repository: CharacterRepository
) : BaseViewModel() {
    private val _characterState = MutableStateFlow<UIState<CharacterDto>>(UIState.Loading())
    var characterState: StateFlow<UIState<CharacterDto>> = _characterState

    fun fetchCharacter(id: Int) {
        _characterState.subscribeTo {
            repository.fetchCharacter(id)
        }
    }
}