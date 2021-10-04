package com.eggtart.booktobookcompose.navigation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.eggtart.booktobookcompose.screen.ChatScreen
import com.eggtart.booktobookcompose.screen.content.ContentScreen
import com.eggtart.booktobookcompose.screen.initial.InitialSettingScreen
import com.eggtart.booktobookcompose.screen.content.bottom.BookcaseScreen
import com.eggtart.booktobookcompose.screen.content.bottom.HomeScreen
import com.eggtart.booktobookcompose.screen.content.bottom.ProfileScreen
import com.eggtart.booktobookcompose.screen.login.LoginScreen
import com.eggtart.booktobookcompose.viewmodel.InitialSettingViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

sealed class Screen(val route: String) {
    object LoginScreen : Screen("LoginScreen")
    object InitialSettingScreen : Screen("InitialSettingScreen")
    object BarcodeScanScreen : Screen("BarcodeScanScreen")
    object ContentScreen : Screen("ContentScreen")
}

sealed class BottomNavigation(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Chat : BottomNavigation("Chat", "Chat", Icons.Default.QuestionAnswer)
    object Home : BottomNavigation("Home", "Home", Icons.Default.Home)
    object Bookcase : BottomNavigation("Bookcase", "Bookcase", Icons.Default.ViewList)
    object Profile : BottomNavigation("Profile", "Profile", Icons.Default.AccountCircle)
}


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
        composable(route = Screen.ContentScreen.route) {
            ContentScreen()
        }
    }
}

@ExperimentalPagerApi
@ExperimentalFoundationApi
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