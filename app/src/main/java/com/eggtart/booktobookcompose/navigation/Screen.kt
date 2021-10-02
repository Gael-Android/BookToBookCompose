package com.eggtart.booktobookcompose.navigation

sealed class Screen(val route: String) {
    object LoginScreen : Screen("LoginScreen")
    object InitScreen : Screen("InitScreen")
}
