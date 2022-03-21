package com.example.rickandmortykt.servicelocatior

import com.example.rickandmortykt.data.network.RetrofitClient
import com.example.rickandmortykt.data.repository.CharacterRepository
import com.example.rickandmortykt.data.repository.EpisodeRepository
import com.example.rickandmortykt.data.repository.LocationRepository
import com.example.rickandmortykt.ui.fragment.character.CharacterViewModel
import com.example.rickandmortykt.ui.fragment.character.charactersDetail.CharactersDetailViewModel
import com.example.rickandmortykt.ui.fragment.episode.EpisodeViewModel
import com.example.rickandmortykt.ui.fragment.episode.episodesDetail.EpisodesDetailViewModel
import com.example.rickandmortykt.ui.fragment.location.LocationViewModel
import com.example.rickandmortykt.ui.fragment.location.locationsDetail.LocationsDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitClient() }
    single { get<RetrofitClient>().provideCharacterApiService() }
    single { get<RetrofitClient>().provideEpisodeApiService() }
    single { get<RetrofitClient>().provideLocationApiService() }
}
val repositoriesModule = module {
    factory { CharacterRepository(get()) }
    factory { EpisodeRepository(get()) }
    factory { LocationRepository(get()) }
}

val viewModelsModule = module {
    viewModel { CharacterViewModel(get()) }
    viewModel { EpisodeViewModel(get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { CharactersDetailViewModel(get()) }
    viewModel { EpisodesDetailViewModel(get()) }
    viewModel { LocationsDetailViewModel(get()) }
}