package com.android.tikalarcorefuse.roomslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.android.tikalarcorefuse.R
import com.android.tikalarcorefuse.data.Room
import com.android.tikalarcorefuse.data.source.GameRepository
import com.android.tikalarcorefuse.databinding.FragmentRoomListBinding

class RoomListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentRoomListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val layoutManager = LinearLayoutManager(context, VERTICAL, false)
        val adapter = RoomsAdapter()

        binding.layoutManager = layoutManager
        binding.recyclerView.addItemDecoration(DividerItemDecoration(context, VERTICAL))
        binding.recyclerView.adapter = adapter
        binding.clickListener = createClickListener()

        getRooms(adapter)

        return binding.root
    }


    fun getRooms(adapter: RoomsAdapter) {

        GameRepository.instance.roomsLiveData.observe(this, Observer { rooms: List<Room> ->
            adapter.submitList(rooms)
        })
        GameRepository.instance.getRooms()


    }

    private fun createClickListener(): View.OnClickListener? {
        return View.OnClickListener {
            findNavController().navigate(R.id.action_roomListFragment_to_createRoom)
        }
    }
}