package com.android.tikalarcorefuse.waiting


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.tikalarcorefuse.R
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 */
class GameWaitFragment : Fragment() {

    lateinit var viewModel: GameWaitViewModel
    lateinit var viewModelFactory: WaitViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val roomId = arguments?.getString("RoomId")
        Timber.i("onCreate: ${roomId}")
        roomId?.let {
            viewModelFactory = WaitViewModelFactory(it)
            viewModel = ViewModelProvider(this, viewModelFactory).get(GameWaitViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wait_game, container, false)
    }



}
