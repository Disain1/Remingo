package com.disain.remingo.reminder.presentation.reminders

import androidx.compose.runtime.Stable
import com.disain.remingo.reminder.data.local.Reminder

data class ReminderUiState(
    @Stable val reminders: List<Reminder> = emptyList(),
    val editReminder: Reminder? = null,
    val isLoading: Boolean = false
)
