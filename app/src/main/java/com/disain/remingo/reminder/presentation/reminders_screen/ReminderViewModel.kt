package com.disain.remingo.reminder.presentation.reminders_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.disain.remingo.reminder.domain.use_cases.reminder.ReminderUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val reminderUseCases: ReminderUseCases
) : ViewModel() {
    private val _uiState = mutableStateOf(ReminderUiState())
    val uiState: State<ReminderUiState> = _uiState

    init {
        getReminders()
    }

    private fun getReminders() {
        _uiState.value = ReminderUiState(isLoading = true)

        reminderUseCases.getRemindersUseCase().onEach {
            _uiState.value = ReminderUiState(reminders = it)
        }.launchIn(viewModelScope)

        _uiState.value = ReminderUiState(isLoading = false)
    }
}