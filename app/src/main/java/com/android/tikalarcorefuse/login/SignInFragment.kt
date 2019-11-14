package com.android.tikalarcorefuse.login

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController

import com.android.tikalarcorefuse.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.fragment_sign_in.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
class SignInFragment : Fragment() {

    lateinit var gso : GoogleSignInOptions
    lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureScreen()
    }

    private fun configureScreen() {
        signInButton.setOnClickListener {
            signIn()
        }

        signOutButton.setOnClickListener{
            signOut()
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                // [START_EXCLUDE]
                updateUI(null)
                // [END_EXCLUDE]
            }
        }
    }
    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {

        // [START_EXCLUDE silent]
        // [END_EXCLUDE]

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Snackbar.make(main_layout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }
    private fun updateUI(user: FirebaseUser?) {
//        hideProgressDialog()
        if (user != null) {
            status.text = getString(R.string.google_status_fmt, user.email)
            detail.text = getString(R.string.firebase_status_fmt, user.uid)

            signInButton.visibility = View.GONE
            signOutAndDisconnect.visibility = View.VISIBLE
            findNavController(this).navigate(R.id.action_signInFragment_to_createRoom)
        } else {
            status.setText(R.string.signed_out)
            detail.text = null

            signInButton.visibility = View.VISIBLE
            signOutAndDisconnect.visibility = View.GONE
        }
    }

    fun signIn(){
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun signOut(){
        auth.signOut()

        // Google sign out
        googleSignInClient.signOut().addOnCompleteListener {
            updateUI(null)
        }
    }

    companion object{

        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }
}
