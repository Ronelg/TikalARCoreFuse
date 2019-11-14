package com.android.tikalarcorefuse.createroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.tikalarcorefuse.R
import com.android.tikalarcorefuse.data.Room
import com.android.tikalarcorefuse.data.source.GameRepository
import kotlinx.android.synthetic.main.fragment_create_room.*

class CreateRoomFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_create_room, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnDone.setOnClickListener {
            addRoom()
        }

    }

    private fun addRoom() {
        GameRepository.instance.addRooms(
            Room(
                name = etName.text.toString(),
                numOfUser = etNumOfUsers.text.toString().toInt()
            )
        )
        findNavController().navigate(R.id.action_createRoom_to_roomListFragment)
    }
}