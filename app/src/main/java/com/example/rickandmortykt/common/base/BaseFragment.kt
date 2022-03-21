package com.example.rickandmortykt.common.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle

import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.rickandmortykt.ui.state.UIState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<ViewModel : BaseViewModel, Binding : ViewBinding>(@LayoutRes fragmentId: Int) :
    Fragment(fragmentId) {

    protected abstract val viewModel: ViewModel
    protected abstract val binding: ViewBinding

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupRequests()
        setupObservers()
    }

    open fun initialize() {}
    open fun setupRequests() {}
    open fun setupObservers() {}

    protected fun <T> StateFlow<UIState<T>>.subscribe(
        state: Lifecycle.State = Lifecycle.State.STARTED,
        action: (UIState<T>) -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(state) {
                this@subscribe.collect {
                    action(it)
                }
            }
        }
    }
}