package com.disain.remingo.reminder.presentation.reminders

import com.disain.remingo.reminder.data.local.Reminder

sealed class RemindersScreenEvent {
    data class addReminder(val reminder: Reminder) : RemindersScreenEvent()
    data class deleteReminder(val reminder: Reminder) : RemindersScreenEvent()
    data class editReminder(val reminder: Reminder) : RemindersScreenEvent()
}
