package com.example.rickandmortykt.ui.fragment.episode

import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortykt.R
import com.example.rickandmortykt.common.base.BaseFragment
import com.example.rickandmortykt.databinding.FragmentEpisodeBinding
import com.example.rickandmortykt.ui.adapter.EpisodeAdapter
import com.example.rickandmortykt.ui.adapter.EpisodeAdapterOffline
import com.example.rickandmortykt.ui.adapter.paging.LoadStateAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class EpisodeFragment : BaseFragment<EpisodeViewModel, FragmentEpisodeBinding>(
    R.layout.fragment_episode
) {
    override val viewModel: EpisodeViewModel by viewModel()
    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    private val episodeAdapter = EpisodeAdapter(
        this::setOnItemClickListener
    )

    private val episodeAdapterOffline = EpisodeAdapterOffline()

    override fun initialize() {
        setupCharacterRecycler()
    }

    private fun setupCharacterRecycler() = with(binding) {
        rvEpisodes.layoutManager = LinearLayoutManager(requireActivity())
        if (isOnline(context)) {
            rvEpisodes.adapter = episodeAdapter.withLoadStateFooter(
                LoadStateAdapter {
                    episodeAdapter.retry()
                }
            )

            episodeAdapter.addLoadStateListener { loadStates ->
                rvEpisodes.isVisible = loadStates.refresh is LoadState.NotLoading
                progressBar.isVisible = loadStates.refresh is LoadState.Loading
                swipeRefresh.isRefreshing = loadStates.refresh is LoadState.Loading
            }

            swipeRefresh.setOnRefreshListener {
                episodeAdapter.refresh()
            }
        } else {
            rvEpisodes.adapter = episodeAdapterOffline
        }
    }

    override fun setupRequests() {
        if (isOnline(context)) {
            viewModel.fetchEpisode().observe(requireActivity()) {
                this.lifecycleScope.launch {
                    episodeAdapter.submitData(it)
                }
                if (episodeAdapter.snapshot().items.isNotEmpty()) {
                    viewModel.insertAllEpisodes(episodeAdapter.snapshot().items)
                }
            }
        } else {
            viewModel.getEpisodes().observe(requireActivity()) {
                if (it.isNotEmpty()) {
                    episodeAdapterOffline.submitList(it)
                    binding.progressBar.isVisible = false
                }
            }
        }
    }

    private fun setOnItemClickListener(id: Int) {

        findNavController().navigate(
            EpisodeFragmentDirections
                .actionEpisodeFragmentToEpisodesDetailFragment(id)
        )
    }
}