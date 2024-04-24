package com.disain.remingo.reminder.domain.repository

import com.disain.remingo.reminder.data.local.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderRepository {
    fun getAllReminders(): Flow<List<Reminder>>
    suspend fun addReminder(reminder: Reminder)
    fun deleteReminder(reminder: Reminder)
}