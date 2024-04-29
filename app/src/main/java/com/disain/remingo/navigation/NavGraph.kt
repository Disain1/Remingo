package com.disain.remingo.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.disain.remingo.reminder.presentation.reminders.ReminderViewModel
import com.disain.remingo.reminder.presentation.reminders.RemindersScreen
import com.disain.remingo.utils.Screen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Reminders.route,
    ) {
        composable(Screen.Reminders.route) {
            val viewModel: ReminderViewModel = hiltViewModel()

            RemindersScreen(
                navController = navController,
                onEvent = viewModel::onEvent,
                uiState = viewModel.uiState.value
            )
        }

        composable(Screen.EditReminder.route) {

        }
    }
}