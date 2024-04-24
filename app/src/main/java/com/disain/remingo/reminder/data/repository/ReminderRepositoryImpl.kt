package com.disain.remingo.reminder.data.repository

import com.disain.remingo.data.AppDatabase
import com.disain.remingo.reminder.data.local.Reminder
import com.disain.remingo.reminder.data.local.ReminderDao
import com.disain.remingo.reminder.domain.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReminderRepositoryImpl @Inject constructor(
    private val reminderDao: ReminderDao
): ReminderRepository {
    override fun getAllReminders(): Flow<List<Reminder>> {
        return reminderDao.getAllReminders()
    }

    override suspend fun addReminder(reminder: Reminder) {
        reminderDao.addReminder(reminder)
    }

    override fun deleteReminder(reminder: Reminder) {
        reminderDao.deleteReminder(reminder)
    }
}