package com.example.rickandmortykt.ui.fragment.character

import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortykt.R
import com.example.rickandmortykt.common.base.BaseFragment
import com.example.rickandmortykt.databinding.FragmentCharacterBinding
import com.example.rickandmortykt.ui.adapter.CharacterAdapter
import com.example.rickandmortykt.ui.adapter.CharacterAdapterOffline
import com.example.rickandmortykt.ui.adapter.paging.LoadStateAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterFragment : BaseFragment<CharacterViewModel, FragmentCharacterBinding>(R.layout.fragment_character) {

    override val viewModel: CharacterViewModel by viewModel()
    override val binding by viewBinding(FragmentCharacterBinding::bind)

    private val characterAdapter = CharacterAdapter(
        this::setOnItemClickListener,
        this::setOnItemLongClickListener
    )

    private val characterAdapterOffline = CharacterAdapterOffline()

    override fun initialize() {
        setupCharacterRecycler()
    }

    private fun setupCharacterRecycler() = with(binding) {
        rvCharacters.layoutManager = LinearLayoutManager(requireActivity())
        if (isOnline(requireContext())) {
            rvCharacters.adapter = characterAdapter.withLoadStateFooter(
                LoadStateAdapter {
                    characterAdapter.retry()
                })

            characterAdapter.addLoadStateListener { loadStates ->
                rvCharacters.isVisible = loadStates.refresh is LoadState.NotLoading
                progressBar.isVisible = loadStates.refresh is LoadState.Loading
                swipeRefresh.isRefreshing = loadStates.refresh is LoadState.Loading
            }
            swipeRefresh.setOnRefreshListener {
                characterAdapter.refresh()
            }
        } else {
            rvCharacters.adapter = characterAdapterOffline
        }
    }

    override fun setupRequests() {
        if (isOnline(context)) {
            viewModel.fetchCharacters().observe(requireActivity()) {
                this.lifecycleScope.launch {
                    characterAdapter.submitData(it)
                }
                if (characterAdapter.snapshot().items.isNotEmpty()) {
                    viewModel.insertAllCharacters(characterAdapter.snapshot().items)
                }
            }
        } else {
            viewModel.getCharacters().observe(requireActivity()) {
                if (it.isNotEmpty()) {
                    characterAdapterOffline.submitList(it)
                    binding.progressBar.isVisible = false
                }
            }
        }
    }

    private fun setOnItemClickListener(id: Int) {
        findNavController().navigate(
            CharacterFragmentDirections
                .actionCharacterFragmentToCharactersDetailFragment(id)
        )
    }

    private fun setOnItemLongClickListener(image: String) {
        findNavController().navigate(
            CharacterFragmentDirections
                .actionCharacterFragmentToCharacterDialogFragment(image)

        )
    }
}