package com.disain.remingo.reminder.domain.use_cases.reminder

data class ReminderUseCases(
    val addReminderUseCase: AddReminderUseCase,
    val getRemindersUseCase: GetRemindersUseCase,
    val deleteReminderUseCase: DeleteReminderUseCase
)
