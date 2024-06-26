package com.disain.remingo.reminder.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Reminder(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val name: String,
    val description: String,
    val timestamp: Long,
    val enabled: Boolean,
    val color: Long
) {
    companion object {
        val reminders = listOf(
            Reminder(
                name = "Тестовое напоминание",
                description = "Практический опыт показывает, что дальнейшее развитие различных форм деятельности позволяет оценить значение ключевых компонентов планируемого обновления.",
                timestamp = 12213213L,
                enabled = true,
                color = 0xFFFFFFFF
            )
        )
    }
}

enum class ReminderCategory() {
    WORK, HEALTH, WORKOUT, NUTRITION, MEETING, SHOPPING, MEDITATION, EDUCATION
}