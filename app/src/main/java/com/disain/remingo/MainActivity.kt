package com.disain.remingo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.disain.remingo.navigation.NavGraph
import com.disain.remingo.notifications.ReminderNotificationService
import com.disain.remingo.ui.theme.RemingoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val notificationService = ReminderNotificationService(this)

        if (intent?.action == "update") {
            intent.extras?.getInt("id")?.let {
                notificationService.deleteNotification(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onNewIntent(intent)

        setContent {
            RemingoTheme {
                NavGraph()
            }
        }
    }
}