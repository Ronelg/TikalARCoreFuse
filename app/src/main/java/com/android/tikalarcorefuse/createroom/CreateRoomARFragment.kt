package com.android.tikalarcorefuse.createroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.tikalarcorefuse.R
import timber.log.Timber.d

class CreateRoomARFragment : Fragment() {

    var roomId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        roomId = arguments?.getString("roomId")
        d(roomId)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_room_ar, container, false)
    }
}