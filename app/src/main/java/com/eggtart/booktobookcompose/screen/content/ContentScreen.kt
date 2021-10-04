package com.eggtart.booktobookcompose.screen.content

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eggtart.booktobookcompose.navigation.BottomNavigation
import com.eggtart.booktobookcompose.screen.ChatScreen
import com.eggtart.booktobookcompose.screen.content.bottom.BookcaseScreen
import com.eggtart.booktobookcompose.screen.content.bottom.EnrollScreen
import com.eggtart.booktobookcompose.screen.content.bottom.HomeScreen
import com.eggtart.booktobookcompose.screen.content.bottom.ProfileScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun ContentScreen() {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            bottomBar = { BottomBar(navController) }
        ) {
            NavHost(navController = navController, startDestination = BottomNavigation.Home.route) {
                composable(BottomNavigation.Chat.route) {
                    ChatScreen()
                }
                composable(BottomNavigation.Home.route) {
                    HomeScreen()
                }
                composable(BottomNavigation.Bookcase.route) {
                    BookcaseScreen()
                }
                composable(BottomNavigation.Profile.route) {
                    ProfileScreen()
                }
                composable(BottomNavigation.Enroll.route) {
                    EnrollScreen()
                }
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        BottomNavigation.Chat,
        BottomNavigation.Home,
        BottomNavigation.Bookcase,
        BottomNavigation.Profile,
        BottomNavigation.Enroll
    )

    BottomNavigation(elevation = 10.dp) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.map {
            BottomNavigationItem(
                icon = {
                    Icon(
                        it.icon,
                        contentDescription = it.title
                    )
                },
                selected = currentRoute == it.route,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(alpha = 0.4f),
                onClick = {
                    navController.navigate(it.route)
                }
            )
        }
    }
}