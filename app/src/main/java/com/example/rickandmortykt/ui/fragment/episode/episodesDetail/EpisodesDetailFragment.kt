package com.example.rickandmortykt.ui.fragment.episode.episodesDetail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortykt.common.base.BaseFragment
import com.example.rickandmortykt.R
import com.example.rickandmortykt.databinding.FragmentEpisodesDetailBinding
import com.example.rickandmortykt.ui.state.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesDetailFragment : BaseFragment<EpisodesDetailViewModel, FragmentEpisodesDetailBinding>(
    R.layout.fragment_episodes_detail) {
    override val viewModel: EpisodesDetailViewModel by viewModels()
    override val binding by viewBinding(FragmentEpisodesDetailBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchEpisode(EpisodesDetailFragmentArgs.fromBundle(requireArguments()).id)
    }

    override fun setupRequests() = with(binding) {
        viewModel.episodeState.subscribe {
            progressBar.isVisible = it is UIState.Loading
            characterGroup.isVisible = it !is UIState.Loading
            when (it) {
                is UIState.Error -> {
                }
                is UIState.Loading -> {
                }
                is UIState.Success -> {
                    it.data?.let { data ->
                        detailName.text = data.name
                        detailAirDate.text = data.air_date
                        detailEpisode.text = data.episode
                        detailUrl.text = data.url
                    }
                }
            }
        }
    }
}