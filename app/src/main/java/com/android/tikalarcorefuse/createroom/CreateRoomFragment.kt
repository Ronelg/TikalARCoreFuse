package com.android.tikalarcorefuse.createroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.tikalarcorefuse.R
import com.google.firebase.firestore.FirebaseFirestore

class CreateRoomFragment : Fragment() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_create_room, container, false
        )
    }
}