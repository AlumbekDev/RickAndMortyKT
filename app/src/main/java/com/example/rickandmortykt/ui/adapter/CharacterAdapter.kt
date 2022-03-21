package com.example.rickandmortykt.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortykt.R
import com.example.rickandmortykt.common.base.BaseComparator
import com.example.rickandmortykt.data.network.dto.character.CharacterDto
import com.example.rickandmortykt.databinding.ItemCharacterBinding

class CharacterAdapter(
    private val onItemClick: (id: Int) -> Unit,
    private val onItemLongClick: (image: String) -> Unit
) : PagingDataAdapter<CharacterDto, CharacterAdapter.CharacterViewHolder>(
    BaseComparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    inner class CharacterViewHolder(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition).apply {
                    onItemClick(this!!.id)
                }
            }
        }

        init {
            itemView.setOnLongClickListener {
                getItem(absoluteAdapterPosition)?.let {
                    onItemLongClick(it.image)
                }
                return@setOnLongClickListener false
            }
        }

        fun onBind(item: CharacterDto) = with(binding) {
            itemImg.load(item.image)
            itemName.text = item.name
            itemStatus.text = item.status
            itemSpecies.text = item.species
            itemLocation.text = item.location.name
            when (item.status) {
                "Alive" -> itemStatusLive.setBackgroundResource(R.drawable.circle_live)
                "Dead" -> itemStatusLive.setBackgroundResource(R.drawable.circle_dead)
                else -> itemStatusLive.setBackgroundResource(R.drawable.circle_unkown)
            }
        }
    }
}