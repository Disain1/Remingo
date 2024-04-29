package com.disain.remingo.reminder.presentation.reminders

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.disain.remingo.notifications.ReminderNotificationService
import com.disain.remingo.reminder.data.local.Reminder
import com.disain.remingo.reminder.domain.use_cases.reminder.ReminderUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val reminderUseCases: ReminderUseCases,
    private val notificationService: ReminderNotificationService
) : ViewModel() {
    private val _uiState = mutableStateOf(ReminderUiState())
    val uiState: State<ReminderUiState> = _uiState

    init {
        getReminders()
        updateNotifications()
    }

    fun onEvent(event: RemindersScreenEvent) {
        when (event) {
            is RemindersScreenEvent.addReminder -> {
                addReminder(event.reminder)
            }
            is RemindersScreenEvent.deleteReminder -> {
                deleteReminder(event.reminder)
            }

            is RemindersScreenEvent.editReminder -> {
                editReminder(event.reminder)
            }
        }
    }

    private fun editReminder(reminder: Reminder) {
        _uiState.value = _uiState.value.copy(
            editReminder = reminder
        )
    }

    private fun addReminder(reminder: Reminder) {
        viewModelScope.launch {
            reminderUseCases.addReminderUseCase(reminder = reminder)
        }

        updateNotifications()
    }

    private fun deleteReminder(reminder: Reminder) {
        viewModelScope.launch {
            reminderUseCases.deleteReminderUseCase(reminder = reminder)
            notificationService.deleteNotification(reminder)
        }
    }

    private fun updateNotifications() {
        reminderUseCases.getRemindersUseCase().onEach { reminders ->
            val notificationIds =
                notificationService.getActiveNotifications()?.map { it.id } ?: emptyList()

            reminders.forEach { reminder ->
                if (!notificationIds.contains(reminder.id)) {
                    notificationService.showNotification(reminder)
                }

                if (!reminder.enabled) {
                    notificationService.deleteNotification(reminder)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getReminders() {
        _uiState.value = ReminderUiState(isLoading = true)

        reminderUseCases.getRemindersUseCase().onEach {
            _uiState.value = ReminderUiState(reminders = it)
        }.launchIn(viewModelScope)
    }
}