package com.android.tikalarcorefuse.landing

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.tikalarcorefuse.R
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    enum class AuthenticationState {
        UNAUTHENTICATED,        // Initial state, the user needs to authenticate
        AUTHENTICATED  ,        // The user has authenticated successfully
        INVALID_AUTHENTICATION  // Authentication failed
    }
    lateinit var gso : GoogleSignInOptions
    lateinit var auth : FirebaseAuth

    val authLiveData = MutableLiveData<AuthenticationState>()

    init {
        authLiveData.postValue(AuthenticationState.UNAUTHENTICATED)
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(application.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        auth = FirebaseAuth.getInstance()
    }

    fun updateAuth(){
        if(auth.currentUser != null){
            authLiveData.postValue(AuthenticationState.AUTHENTICATED)
        } else {
            authLiveData.postValue(AuthenticationState.UNAUTHENTICATED)
        }
    }
}