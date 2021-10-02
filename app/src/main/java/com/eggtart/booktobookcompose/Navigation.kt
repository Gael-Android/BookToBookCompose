package com.eggtart.booktobookcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.Glide.init
import com.eggtart.booktobookcompose.activity.InitialSettingScreen
import com.eggtart.booktobookcompose.activity.LoginScreen
import com.eggtart.booktobookcompose.activity.TestScreen
import com.eggtart.booktobookcompose.viewmodel.InitialSettingViewModel

@Composable
fun Navigation() {
    Log.d("KWK", "Navigation")

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = Screen.InitScreen.route) {
            val viewModel: InitialSettingViewModel = viewModel()
            InitialSettingScreen(viewModel)
        }
    }
}

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("KWK", "HomeActivity")

        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}