package com.disain.remingo.reminder.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Query("SELECT * FROM reminder")
    fun getAllReminders(): Flow<List<Reminder>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addReminder(reminder: Reminder)

    @Delete
    fun deleteReminder(reminder: Reminder)
}