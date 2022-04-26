package com.example.rickandmortykt.ui.fragment.location

import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortykt.R
import com.example.rickandmortykt.common.base.BaseFragment
import com.example.rickandmortykt.databinding.FragmentLocationBinding
import com.example.rickandmortykt.ui.adapter.LocationAdapter
import com.example.rickandmortykt.ui.adapter.LocationAdapterOffline
import com.example.rickandmortykt.ui.adapter.paging.LoadStateAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationFragment : BaseFragment<LocationViewModel, FragmentLocationBinding>(
    R.layout.fragment_location
) {
    override val viewModel: LocationViewModel by viewModel()
    override val binding by viewBinding(FragmentLocationBinding::bind)
    private val locationAdapter = LocationAdapter(
        this::setOnItemClickListener
    )

    private val locationAdapterOffline = LocationAdapterOffline()

    override fun initialize() {
        setupCharacterRecycler()
    }

    private fun setupCharacterRecycler() = with(binding) {
        rvLocations.layoutManager = LinearLayoutManager(requireActivity())
        if (isOnline(context)) {
            rvLocations.adapter = locationAdapter.withLoadStateFooter(
                LoadStateAdapter {
                    locationAdapter.retry()
                })

            locationAdapter.addLoadStateListener { loadStates ->
                rvLocations.isVisible = loadStates.refresh is LoadState.NotLoading
                progressBar.isVisible = loadStates.refresh is LoadState.Loading
                swipeRefresh.isRefreshing = loadStates.refresh is LoadState.Loading
            }

            swipeRefresh.setOnRefreshListener {
                locationAdapter.refresh()
            }
        } else {
            rvLocations.adapter = locationAdapterOffline
        }
    }

    override fun setupRequests() {
        if (isOnline(context)) {
            viewModel.fetchLocations().observe(requireActivity()) {
                this.lifecycleScope.launch {
                    locationAdapter.submitData(it)
                }
                if (locationAdapter.snapshot().items.isNotEmpty()) {
                    viewModel.insertAllLocations(locationAdapter.snapshot().items)
                }
            }
        } else {
            viewModel.getLocations().observe(requireActivity()) {
                if (it.isNotEmpty()) {
                    locationAdapterOffline.submitList(it)
                    binding.progressBar.isVisible = false
                }
            }
        }
    }

    private fun setOnItemClickListener(id: Int) {
        findNavController().navigate(
            LocationFragmentDirections
                .actionLocationFragmentToLocationsDetailFragment(id)
        )
    }
}