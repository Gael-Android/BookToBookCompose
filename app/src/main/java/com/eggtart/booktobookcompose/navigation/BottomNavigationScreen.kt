package com.eggtart.booktobookcompose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Chat : BottomNavigationScreen("Chat", "Chat", Icons.Default.QuestionAnswer)
    object Home : BottomNavigationScreen("Home", "Home", Icons.Default.Home)
    object BookCase : BottomNavigationScreen("BookCase", "BookCase", Icons.Default.ViewList)
    object Profile : BottomNavigationScreen("Profile", "Profile", Icons.Default.AccountCircle)
}