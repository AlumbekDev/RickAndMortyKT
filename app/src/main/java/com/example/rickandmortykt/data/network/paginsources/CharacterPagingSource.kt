package com.example.rickandmortykt.data.network.paginsources

import com.example.rickandmortykt.common.base.BasePagingSource
import com.example.rickandmortykt.data.network.api.CharacterApiService
import com.example.rickandmortykt.data.network.dto.character.CharacterDto

class CharacterPagingSource(
    private val service: CharacterApiService
) : BasePagingSource<CharacterDto>({ position ->
    service.fetchCharacters(position)

})