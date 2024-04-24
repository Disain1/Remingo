package com.disain.remingo.di

import android.app.Application
import androidx.room.Room
import com.disain.remingo.App
import com.disain.remingo.data.AppDatabase
import com.disain.remingo.reminder.data.repository.ReminderRepositoryImpl
import com.disain.remingo.reminder.domain.repository.ReminderRepository
import com.disain.remingo.reminder.domain.use_cases.reminder.AddReminderUseCase
import com.disain.remingo.reminder.domain.use_cases.reminder.GetRemindersUseCase
import com.disain.remingo.reminder.domain.use_cases.reminder.ReminderUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesAppDatabase(context: Application): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providesReminderRepository(database: AppDatabase): ReminderRepository {
        return ReminderRepositoryImpl(database.reminderDao)
    }

    @Singleton
    @Provides
    fun providesReminderUseCases(reminderRepository: ReminderRepository): ReminderUseCases {
        return ReminderUseCases(
            addReminderUseCase = AddReminderUseCase(reminderRepository),
            getRemindersUseCase = GetRemindersUseCase(reminderRepository)
        )
    }
}