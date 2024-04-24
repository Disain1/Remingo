package com.disain.remingo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.disain.remingo.components.Screen
import com.disain.remingo.reminder.data.local.Reminder
import com.disain.remingo.reminder.presentation.reminders_screen.ReminderUiState
import com.disain.remingo.reminder.presentation.reminders_screen.ReminderViewModel
import com.disain.remingo.reminder.presentation.reminders_screen.RemindersScreen
import com.example.compose.RemingoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RemingoTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Reminders.route,
                ) {
                    composable(Screen.Reminders.route) {
                        val viewModel: ReminderViewModel = hiltViewModel()

                        RemindersScreen(
                            uiState = viewModel.uiState.value
                        )
                    }
                }
            }
        }
    }
}