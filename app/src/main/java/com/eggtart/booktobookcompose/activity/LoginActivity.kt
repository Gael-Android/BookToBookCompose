package com.eggtart.booktobookcompose.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.eggtart.booktobookcompose.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.BuildConfig
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : ComponentActivity() {
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        onSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
        )

        val signInIntent = AuthUI
            .getInstance()
            .createSignInIntentBuilder()
            .setIsSmartLockEnabled(!BuildConfig.DEBUG, true)
            .setAvailableProviders(providers)
            .setLogo(R.drawable.logo)
            .build()

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            Log.d("KWK", "Hello ${user.displayName}")
        } else {
            signInLauncher.launch(signInIntent)
        }

//        signInLauncher.launch(signInIntent)
    }
}

private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
    val response = result.idpResponse
    if (result.resultCode == AppCompatActivity.RESULT_OK) {
        val user = FirebaseAuth.getInstance().currentUser
        Log.d("KWK", "NEW LOGIN : ${user?.uid} / ${user?.email}")
    } else {
        Log.d("KWK", response?.error.toString())
    }
}
