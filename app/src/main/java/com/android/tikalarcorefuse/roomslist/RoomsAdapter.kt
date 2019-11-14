package com.android.tikalarcorefuse.roomslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.tikalarcorefuse.databinding.RoomItemBinding

class RoomsAdapter() :
    ListAdapter<RoomObject, RoomsAdapter.RoomViewHolder>(RoomDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding = RoomItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val roomObject = getItem(position)
        holder.apply {
            bind(roomObject)
            itemView.tag = roomObject
        }
    }


    inner class RoomViewHolder(
        private val binding: RoomItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(room: RoomObject) {
            binding.roomName = room.name
        }
    }
}

private class RoomDiffCallback : DiffUtil.ItemCallback<RoomObject>() {
    override fun areItemsTheSame(oldItem: RoomObject, newItem: RoomObject): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RoomObject, newItem: RoomObject): Boolean {
        return oldItem.name == newItem.name
    }

}
