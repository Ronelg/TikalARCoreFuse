package com.android.tikalarcorefuse.roomslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.tikalarcorefuse.R
import com.android.tikalarcorefuse.data.Room

class RoomsAdapter(val callback: ((String?) -> Unit)?) :
    ListAdapter<Room, RoomsAdapter.RoomViewHolder>(RoomDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.room_item, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = getItem(position)
        holder.bind(room)
    }

    inner class RoomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView? = null
        var subtitle: TextView? = null

        init {
            title = view.findViewById(R.id.title)
            subtitle = view.findViewById(R.id.subtitle)
        }

        fun bind(room: Room) {
            title?.text = room.name
            subtitle?.text = "Users ${room.numOfUser}"
            itemView.setOnClickListener { callback?.invoke(room.id) }

        }
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
