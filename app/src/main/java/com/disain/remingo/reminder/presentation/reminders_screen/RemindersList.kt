package com.disain.remingo.reminder.presentation.reminders_screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.disain.remingo.reminder.data.local.Reminder
import com.example.compose.RemingoTheme

@Composable
fun RemindersList(
    listState: LazyListState,
    reminders: List<Reminder>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        state = listState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(reminders) {
            ReminderItem(
                reminder = it,
                modifier = Modifier,
                onClick = { }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReminderItem(
    reminder: Reminder,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {
    val context = LocalContext.current

    Surface(
        tonalElevation = 1.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .combinedClickable(
                onLongClick = {
                    Toast
                        .makeText(context, "Удалено", Toast.LENGTH_LONG)
                        .show()
                },
                onClick = {
                    onClick(reminder.id)
                }
            )
    ) {

        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = RoundedCornerShape(5.dp)
                ),
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "6 марта, 21:36",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }

            Text(text = reminder.name, style = MaterialTheme.typography.titleLarge)

            if (reminder.description.isNotBlank()) {
                Text(text = reminder.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun ReminderItemPreview() {
    RemingoTheme {
        ReminderItem(
            reminder = Reminder.reminders[0],
            onClick = {}
        )
    }
}