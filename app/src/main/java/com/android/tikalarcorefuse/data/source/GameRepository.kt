package com.android.tikalarcorefuse.data.source

import androidx.lifecycle.MutableLiveData
import com.android.tikalarcorefuse.data.Room
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber.d
import timber.log.Timber.e


class GameRepository private constructor() {

    private val ROOM_COLLECTION = "rooms"

    private val db = FirebaseFirestore.getInstance()

    val roomsLiveData = MutableLiveData<List<Room>>()

    val roomsHash = hashMapOf<String, Room>()

    fun addRooms(room: Room) {
        val docData = hashMapOf(
            "name" to room.name,
            "numOfUser" to room.numOfUser,
            "items" to room.items
        )

        val collectionRef = db.collection(ROOM_COLLECTION)

        collectionRef
            .add(docData)
            .addOnSuccessListener {
                d("Room item successfully written!")
            }
            .addOnFailureListener {
                e("Error writing document")
            }

    }

    private fun fetchRooms() {
        val collectionRef = db.collection(ROOM_COLLECTION)
        collectionRef.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val room = document.toObject(Room::class.java)
                    room.id = document.id
                    roomsHash[document.id] = room
                }
                d("Room item successfully read!")
                roomsLiveData.postValue(roomsHash.values.toList())
            }
            .addOnFailureListener {
                e("Error reading document")
            }
    }

    fun getRooms() {
        fetchRooms()
    }

    private object HOLDER {
        val INSTANCE = GameRepository()
    }

    companion object {
        val instance: GameRepository by lazy { HOLDER.INSTANCE }
    }
}