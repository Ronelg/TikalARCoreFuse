package com.android.tikalarcorefuse.data.source

import com.android.tikalarcorefuse.data.Room
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber.d
import timber.log.Timber.e


class GameRepository private constructor() {

    private val ROOM_COLLECTION = "rooms"

    private val db = FirebaseFirestore.getInstance()

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
                e( "Error writing document")
            }

    }
    private object HOLDER {
        val INSTANCE = GameRepository()
    }

    companion object {
        val instance: GameRepository by lazy { HOLDER.INSTANCE }
    }
}