package com.example.rickandmortykt.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortykt.data.network.dto.location.LocationDto
import com.example.rickandmortykt.common.base.BaseComparator
import com.example.rickandmortykt.databinding.LocationItemBinding

class LocationAdapter(
    private val onItemClick: (id: Int) -> Unit,
) : PagingDataAdapter<LocationDto, LocationAdapter.LocationViewHolder>(
    BaseComparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            LocationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    inner class LocationViewHolder(
        private val binding: LocationItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition).apply {
                    onItemClick(this!!.id)
                }
            }
        }

        fun onBind(item: LocationDto) = with(binding) {
            itemName.text = item.name
            itemType.text = item.type
        }
    }
}