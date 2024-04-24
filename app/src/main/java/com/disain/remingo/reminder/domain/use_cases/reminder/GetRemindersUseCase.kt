package com.disain.remingo.reminder.domain.use_cases.reminder

import com.disain.remingo.reminder.data.local.Reminder
import com.disain.remingo.reminder.domain.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRemindersUseCase @Inject constructor(
    private val reminderRepository: ReminderRepository
) {
    operator fun invoke(): Flow<List<Reminder>> {
        return reminderRepository.getAllReminders()
    }
}