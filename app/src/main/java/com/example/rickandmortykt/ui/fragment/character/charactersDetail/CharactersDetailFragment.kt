package com.example.rickandmortykt.ui.fragment.character.charactersDetail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.rickandmortykt.common.base.BaseFragment
import com.example.rickandmortykt.R
import com.example.rickandmortykt.databinding.FragmentCharactersDetailBinding
import com.example.rickandmortykt.ui.state.UIState
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersDetailFragment :
    BaseFragment<CharactersDetailViewModel, FragmentCharactersDetailBinding>(
        R.layout.fragment_characters_detail
    ) {

    override val viewModel: CharactersDetailViewModel by viewModel()
    override val binding by viewBinding(FragmentCharactersDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchCharacter(CharactersDetailFragmentArgs.fromBundle(requireArguments()).id)
    }

    override fun setupRequests() = with(binding) {
        viewModel.characterState.subscribe {
            progressBar.isVisible = it is UIState.Loading
            when (it) {
                is UIState.Loading -> {
                }
                is UIState.Error -> {
                }
                is UIState.Success -> {
                    it.data?.let { data ->
                        detailImg.load(data.image)
                        detailName.text = data.name
                        detailStatus.text = data.status
                        detailSpacial.text = data.species
//                        detailGender.text = data.gender
                        when (data.status) {
                            "Alive" -> detailStatusLive.setBackgroundResource(R.drawable.circle_live)
                            "Dead" -> detailStatusLive.setBackgroundResource(R.drawable.circle_dead)
                            else -> detailStatusLive.setBackgroundResource(R.drawable.circle_unkown)
                        }
                    }
                }
            }
        }
    }
}