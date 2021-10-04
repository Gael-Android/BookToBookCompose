package com.eggtart.booktobookcompose.navigation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eggtart.booktobookcompose.screen.ContentView
import com.eggtart.booktobookcompose.screen.InitialSettingScreen
import com.eggtart.booktobookcompose.screen.LoginScreen
import com.eggtart.booktobookcompose.viewmodel.InitialSettingViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun Navigation() {
    Log.d("KWK", "Navigation")

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = Screen.InitialSettingScreen.route) {
            val viewModel = InitialSettingViewModel(navController)
            InitialSettingScreen(viewModel)
        }
        composable(route = Screen.BarcodeScanScreen.route) {
            ContentView()
        }
        composable(route = Screen.MainScreen.route) {
            ContentView()
        }
    }
}

@ExperimentalPagerApi
@ExperimentalFoundationApi
@OptIn(ExperimentalPagerApi::class)
@ExperimentalMaterialApi
class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("KWK", "HomeActivity")

        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}