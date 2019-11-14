package com.android.tikalarcorefuse.waiting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import timber.log.Timber

class GameWaitViewModel(val roomId : String) : ViewModel() {
    var firestore :FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        val document = firestore.document("/rooms/$roomId")
        document.addSnapshotListener(object : EventListener<DocumentSnapshot>{
            override fun onEvent(document: DocumentSnapshot?, p1: FirebaseFirestoreException?) {
                Timber.i("onEvent: ${document}")
            }

        })
    }
}

class WaitViewModelFactory(val roomId : String) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(String::class.java).newInstance(roomId)
    }

}