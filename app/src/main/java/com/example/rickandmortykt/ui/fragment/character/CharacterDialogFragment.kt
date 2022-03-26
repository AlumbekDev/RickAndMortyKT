package com.example.rickandmortykt.ui.fragment.character

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import coil.load
import com.example.rickandmortykt.databinding.FragmentCharacterDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentCharacterDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentCharacterDialogBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(activity)
            .setView(binding.root)
            .create()
        setupArgs()
        return builder
    }

    private fun setupArgs() = with(binding) {
        dialogImg.load(CharacterDialogFragmentArgs.fromBundle(requireArguments()).image)
    }
}