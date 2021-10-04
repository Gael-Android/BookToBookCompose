package com.eggtart.booktobookcompose.navigation

sealed class Screen(val route: String) {
    object LoginScreen : Screen("LoginScreen")
    object InitialSettingScreen : Screen("InitialSettingScreen")
    object BarcodeScanScreen : Screen("BarcodeScanScreen")
    object MainScreen : Screen("MainScreen")
}
