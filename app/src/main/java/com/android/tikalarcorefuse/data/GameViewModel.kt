package com.android.tikalarcorefuse.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.android.tikalarcorefuse.data.source.GameRepository

class GameViewModel : ViewModel(), Observer<List<Room>> {

    init {
        GameRepository.instance.roomsLiveData.observeForever(this)
    }

    val roomsLiveData = MutableLiveData<List<Room>>()

    fun getRooms() {
        GameRepository.instance.fetchRooms()
    }

    override fun onChanged(list: List<Room>?) {
        roomsLiveData.postValue(list)
    }

    fun addRoom(room: Room, callback: ((String) -> Unit)?) {
        GameRepository.instance.addRooms(room, callback)

    }

    override fun onCleared() {
        GameRepository.instance.roomsLiveData.removeObserver(this)
        super.onCleared()
    }

}