package com.android.tikalarcorefuse.auth

import com.google.firebase.auth.FirebaseAuth

object AppAuthService : BaseAuth {
    val firebaseAuth = FirebaseAuth.getInstance();

    override fun signInWithGoogle() {
    }

    override fun signInWithFacebook() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signInAnonymous() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}