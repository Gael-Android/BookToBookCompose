package com.eggtart.booktobookcompose

sealed class Screen(val route: String) {
    object LoginScreen : Screen("LoginScreen")
    object InitScreen : Screen("InitScreen")
}
