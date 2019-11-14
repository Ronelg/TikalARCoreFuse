package com.android.tikalarcorefuse.landing


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment

import com.android.tikalarcorefuse.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class LandingFragment : Fragment() {

    lateinit var gso : GoogleSignInOptions
    lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("LandingFragment","onCreate: ")
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(activity!!, gso)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_landing, container, false)
    }


    override fun onStart() {
        super.onStart()
        checkLoginAndNavigate()
    }

    private fun checkLoginAndNavigate() {
        val currentUser = auth.currentUser

        if(currentUser == null){
            Log.i("LandingPage", "Not logged in, navigate to signInPage")
            NavHostFragment.findNavController(this).navigate(R.id.action_landingFragment_to_signInFragment)
        } else {
            Log.i("LandingPage","Logged in, navigate to roomsList")
            NavHostFragment.findNavController(this).navigate(R.id.action_landingFragment_to_roomListFragment)
        }

    }

}
