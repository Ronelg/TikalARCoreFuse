package com.android.tikalarcorefuse.roomslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.tikalarcorefuse.R
import com.android.tikalarcorefuse.databinding.FragmentRoomListBinding

class RoomListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentRoomListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = RoomsAdapter()

        binding.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        binding.clickListener = createClickListener()

        adapter.submitList(fakeData().getListOfRooms())

        return binding.root
    }

    private fun createClickListener(): View.OnClickListener? {
        return View.OnClickListener {
            findNavController().navigate(R.id.action_roomListFragment_to_createRoom)
        }
    }

//    companion object {
//        fun newInstance(): RoomListFragment =
//            RoomListFragment()
//    }
}

class fakeData {

    fun getListOfRooms(): List<RoomObject> {
        return listOf(
            RoomObject("room 1"),
            RoomObject("room 2")
        )
    }
}