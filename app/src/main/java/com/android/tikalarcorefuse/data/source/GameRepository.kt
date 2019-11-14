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

    fun getRooms() {
        val collectionRef = db.collection(ROOM_COLLECTION)
        collectionRef.get()
            .addOnSuccessListener {
                if (it.isEmpty) {
                    d("No rooms")
                } else {
                    val rooms = it.toObjects(Room::class.java)

                    roomsLiveData.postValue(rooms)
                    d("Room item successfully read!")
                    d(rooms.toString())
                }

            }
            .addOnFailureListener {
                e("Error reading document")
            }
    }

    private object HOLDER {
        val INSTANCE = GameRepository()
    }

    companion object {
        val instance: GameRepository by lazy { HOLDER.INSTANCE }
    }
}