package com.android.tikalarcorefuse.roomslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.tikalarcorefuse.data.Room
import com.android.tikalarcorefuse.databinding.RoomItemBinding

class RoomsAdapter(val adapterClickListener: AdapterClickListener) :
    ListAdapter<Room, RoomsAdapter.RoomViewHolder>(RoomDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding = RoomItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = getItem(position)

        holder.apply {
            bind(room)
            itemView.tag = room
            itemView.setOnClickListener {
                adapterClickListener.onRoomItemClicked(getItem(position))
            }
        }
    }


    inner class RoomViewHolder(
        private val binding: RoomItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(room: Room) {
            binding.roomName = room.name
            binding.numOfUser = "Users: ${room.numOfUser ?: 0}"
        }
    }


     interface AdapterClickListener{
         fun onRoomItemClicked(room : Room)
     }
}

private class RoomDiffCallback : DiffUtil.ItemCallback<Room>() {
    override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem.name == newItem.name
    }

}
