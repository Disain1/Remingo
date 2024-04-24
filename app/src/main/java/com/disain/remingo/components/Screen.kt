package com.disain.remingo.components

sealed class Screen(val route: String) {
    data object Reminders : Screen(route = "reminders_screen")
}