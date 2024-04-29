package com.disain.remingo.notifications

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.service.notification.StatusBarNotification
import com.disain.remingo.MainActivity
import com.disain.remingo.R
import com.disain.remingo.reminder.data.local.Reminder
import com.disain.remingo.utils.Constants
import javax.inject.Inject


class ReminderNotificationService @Inject constructor(
    private val context: Context
) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun deleteNotification(reminder: Reminder) {
        notificationManager.cancel(reminder.id)
    }

    fun deleteNotification(id: Int) {
        notificationManager.cancel(id)
    }

    fun getActiveNotifications(): Array<out StatusBarNotification>? {
        return notificationManager.activeNotifications
    }

    fun showNotification(reminder: Reminder) {
        val deleteIntent = Intent(context, MainActivity::class.java)
            .setAction("update")
            .putExtra("id", reminder.id)
        val deletePendingIntent = PendingIntent.getActivity(context, 2, deleteIntent, PendingIntent.FLAG_IMMUTABLE)

        val notification = Notification.Builder(context, Constants.notificationChannelId)
            .setSmallIcon(R.drawable.notification)
            .setContentTitle(reminder.name)
            .setContentText(reminder.description)
            .setCategory(Notification.CATEGORY_ALARM)
            .setStyle(Notification.BigTextStyle()
                .bigText(reminder.description))
            .setActions(
                Notification.Action.Builder(
                    Icon.createWithResource(context, R.drawable.notification),
                    "Отметить",
                    deletePendingIntent
                ).build()
            )
            .setAutoCancel(false)
            .setOngoing(true)
            .build()

        notificationManager.notify(
            reminder.id,
            notification
        )
    }
}