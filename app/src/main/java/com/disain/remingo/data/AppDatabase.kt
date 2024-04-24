package com.disain.remingo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.disain.remingo.reminder.data.local.Reminder
import com.disain.remingo.reminder.data.local.ReminderDao

@Database(entities = [Reminder::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract val reminderDao: ReminderDao

    companion object {
        const val DATABASE_NAME = "remingo_db"
    }
}