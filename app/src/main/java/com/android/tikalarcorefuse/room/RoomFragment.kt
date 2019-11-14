package com.android.tikalarcorefuse.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.android.tikalarcorefuse.R
import com.android.tikalarcorefuse.data.Item
import com.android.tikalarcorefuse.data.Room
import com.android.tikalarcorefuse.data.source.GameRepository

class RoomFragment : Fragment() {

    private lateinit var viewModel: RoomViewModel
    private lateinit var items: ArrayList<Item>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.room_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RoomViewModel::class.java)

        //TODO get room object from outside
        val room = Room()
        room.items?.let {
            items = it as ArrayList<Item>
        }
    }

    fun popedBaloon(x: Float, y: Float, z: Float) {
        items.forEach {
            if (it.x == x && it.y == y && it.z == z) {
                items.remove(it)
                return
            }
        }
    }

    fun jjj(){
       // GameRepository.instance.
    }

}
