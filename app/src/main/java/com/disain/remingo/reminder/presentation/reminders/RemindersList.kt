package com.disain.remingo.reminder.presentation.reminders

import android.content.res.Configuration
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.disain.remingo.R
import com.disain.remingo.reminder.data.local.Reminder
import com.disain.remingo.ui.theme.RemingoTheme
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RemindersList(
    listState: LazyListState,
    reminders: List<Reminder>,
    modifier: Modifier = Modifier,
    onReminderClick: (Reminder) -> Unit,
    onDeleteButtonClick: (Reminder) -> Unit,
    onDisableButtonClick: (Reminder) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        state = listState,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(reminders, key = { it.id }) { reminder ->
            ReminderItem(
                reminder = reminder,
                modifier = Modifier
                    .animateItemPlacement()
                    .clickable {
                        onReminderClick(reminder)
                    },
                onDeleteButtonClick = { onDeleteButtonClick(reminder) },
                onDisableButtonClick = { onDisableButtonClick(reminder) }
            )
        }
    }
}

@Composable
fun ReminderItem(
    reminder: Reminder,
    modifier: Modifier = Modifier,
    onDeleteButtonClick: (Int) -> Unit,
    onDisableButtonClick: (Reminder) -> Unit
) {
    val dateFormat = SimpleDateFormat("d MMMM, HH:mm", Locale.getDefault())
    val date = Date(reminder.timestamp)
    val dateText = dateFormat.format(date)

    Surface(
        tonalElevation = if (reminder.enabled) 1.dp else 5.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = RoundedCornerShape(5.dp)
                ),
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = dateText,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }

            Row(
                modifier = Modifier.padding(top = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    modifier = Modifier.size(20.dp),
                    checked = !reminder.enabled,
                    onCheckedChange = { onDisableButtonClick(reminder) })
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = reminder.name,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (!reminder.enabled) TextDecoration.LineThrough else null
                )
            }

            if (reminder.description.isNotBlank()) {
                Text(text = reminder.description, style = MaterialTheme.typography.bodySmall)
            }

//            OutlinedButton(
//                modifier = Modifier,
//                onClick = { onDeleteButtonClick(reminder.id) }
//            ) {
//                Text(text = stringResource(id = R.string.reminder_delete_button))
//            }
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
            onDisableButtonClick = {},
            onDeleteButtonClick = {}
        )
    }
}