package com.android.tikalarcorefuse.roomslist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.android.tikalarcorefuse.R
import com.android.tikalarcorefuse.data.GameViewModel
import com.android.tikalarcorefuse.data.Room
import com.android.tikalarcorefuse.data.ViewModelFactory
import com.android.tikalarcorefuse.data.source.GameRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_room_list.*
import timber.log.Timber

class RoomListFragment : Fragment() {

    private val viewModel: GameViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory.instance).get(GameViewModel::class.java)
    }

    lateinit var adapter: RoomsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_room_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        getRooms()
        createRoomButton.setOnClickListener {
            findNavController().navigate(R.id.action_roomListFragment_to_createRoom)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.app_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Timber.i("onOptionsItemSelected: ")
        if (item.itemId == R.id.logout) {
            try {
                FirebaseAuth.getInstance().signOut()
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_roomListFragment_to_landingFragment)
                return true
            } catch (e: Throwable) {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView() {
        adapter = RoomsAdapter{
                Timber.i("click the id : $it")
            }


        val layoutManager = LinearLayoutManager(context, VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(context, VERTICAL))
        recyclerView.adapter = adapter
    }

    private fun getRooms() {
        viewModel.roomsLiveData?.observe(this, Observer { rooms: List<Room> ->
            adapter.submitList(rooms)
        })
        viewModel.getRooms()
    }


}
