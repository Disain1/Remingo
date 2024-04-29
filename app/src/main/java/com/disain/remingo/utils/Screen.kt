package com.disain.remingo.utils

sealed class Screen(val route: String) {
    data object Reminders : Screen(route = "reminders_screen")
    data object EditReminder : Screen(route = "edit_reminder_screen")
}