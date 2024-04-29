package com.disain.remingo.reminder.domain.use_cases.reminder

import com.disain.remingo.reminder.data.local.Reminder
import com.disain.remingo.reminder.domain.repository.ReminderRepository
import javax.inject.Inject

class DeleteReminderUseCase @Inject constructor(
    private val reminderRepository: ReminderRepository
) {
    suspend operator fun invoke(reminder: Reminder) {
        return reminderRepository.deleteReminder(reminder)
    }
}