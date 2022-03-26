package com.example.rickandmortykt.ui.fragment.location.locationsDetail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortykt.common.base.BaseFragment
import com.example.rickandmortykt.R
import com.example.rickandmortykt.databinding.FragmentLocationsDetailBinding
import com.example.rickandmortykt.ui.state.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationsDetailFragment :
    BaseFragment<LocationsDetailViewModel, FragmentLocationsDetailBinding>(
        R.layout.fragment_locations_detail
    ) {

    override val viewModel: LocationsDetailViewModel by viewModels()
    override val binding by viewBinding(FragmentLocationsDetailBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchLocation(LocationsDetailFragmentArgs.fromBundle(requireArguments()).id)
    }

    override fun setupRequests() = with(binding) {
        viewModel.locationState.subscribe {
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
                        detailType.text = data.type
                        detailDimension.text = data.dimension
                        detailUrl.text = data.url
                    }
                }
            }
        }
    }
}