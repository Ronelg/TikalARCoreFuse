package com.android.tikalarcorefuse.roomslist

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.android.tikalarcorefuse.R
import com.android.tikalarcorefuse.data.Room
import com.android.tikalarcorefuse.data.source.GameRepository
import com.android.tikalarcorefuse.databinding.FragmentRoomListBinding
import com.google.firebase.auth.FirebaseAuth
import timber.log.Timber

class RoomListFragment : Fragment(), RoomsAdapter.AdapterClickListener {


    lateinit var adapter : RoomsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = RoomsAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentRoomListBinding.inflate(inflater, container, false)
        val context = this.context ?: return binding.root

        binding.recyclerView.addItemDecoration(DividerItemDecoration(context, VERTICAL))
        binding.recyclerView.adapter = adapter
        binding.clickListener = createClickListener()

        getRooms(adapter)
        setHasOptionsMenu(true)
        return binding.root
    }

    fun getRooms(adapter: RoomsAdapter) {
        GameRepository.instance.roomsLiveData.observe(this, Observer { rooms: List<Room> ->
            adapter.submitList(rooms)
        })
        GameRepository.instance.getRooms()
    }

    private fun createClickListener(): View.OnClickListener? {
        return View.OnClickListener {
            findNavController().navigate(R.id.action_createRoom_to_createRoomARFragment)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.app_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Timber.i("onOptionsItemSelected: ")
        if(item.itemId == R.id.logout){
            try {
                FirebaseAuth.getInstance().signOut()
                NavHostFragment.findNavController(this).navigate(R.id.action_roomListFragment_to_landingFragment)
                return true
            } catch (e : Throwable){

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRoomItemClicked(room: Room) {
        findNavController().navigate(R.id.action_roomListFragment_to_gameWaitFragment, bundleOf("RoomId" to room.id))
    }

//    companion object {
//        fun newInstance(): RoomListFragment =
//            RoomListFragment()
//    }
}
