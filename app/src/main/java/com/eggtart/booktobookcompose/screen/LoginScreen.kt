package com.eggtart.booktobookcompose.screen

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.eggtart.booktobookcompose.R
import com.eggtart.booktobookcompose.navigation.Screen
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.BuildConfig
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navController: NavController) {
    var isOnceLogIn by remember {
        mutableStateOf(false)
    }

    Log.d("KWK", "LoginScreen")

    val startInitialSettingActivity = {
        Log.d("KWK", "startInitialSettingActivity")
        navController.navigate(Screen.InitScreen.route) {
            popUpTo(Screen.LoginScreen.route) {
                inclusive = true
            }
        }
    }

    val signInLauncher =
        rememberLauncherForActivityResult(FirebaseAuthUIActivityResultContract()) { result ->
            val response = result.idpResponse
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                Log.d("KWK", "NEW LOGIN : ${user?.uid} / ${user?.email}")
                startInitialSettingActivity()
            } else {
                Log.d("KWK", response?.error.toString())
            }
        }

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

    // 로그아웃
//    AuthUI.getInstance()
//        .signOut(LocalContext.current)
    // 로그아웃

    if (!isOnceLogIn) {
        isOnceLogIn = true
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            Log.d("KWK", "Hello ${user.displayName}")
            startInitialSettingActivity()
        } else {
            SideEffect {
                signInLauncher.launch(signInIntent)
            }
        }
    }
}

