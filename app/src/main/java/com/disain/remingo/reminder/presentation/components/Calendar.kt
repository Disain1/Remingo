package com.disain.remingo.reminder.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.disain.remingo.reminder.data.local.Reminder
import com.disain.remingo.ui.theme.RemingoTheme
import java.sql.Timestamp
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

data class ReminderCalendarState(
    val locale: Locale,
    val zone: TimeZone,
    val reminders: List<Reminder>
)

@Composable
fun rememberReminderCalendarState(
    locale: Locale = Locale.getDefault(),
    zone: TimeZone = TimeZone.getDefault(),
    reminders: List<Reminder> = emptyList()
): ReminderCalendarState {
    return remember {
        ReminderCalendarState(
            locale = locale,
            zone = zone,
            reminders = reminders
        )
    }
}

@Composable
private fun RemindersCalendarHeader(monthName: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.ChevronLeft, contentDescription = null)
        }
        Text(text = monthName, style = MaterialTheme.typography.titleLarge)

        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
        }
    }
}

@Composable
private fun RemindersCalendarWeekdayTitles(
    modifier: Modifier = Modifier
) {


//    LazyRow(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(top = 6.dp),
//        horizontalArrangement = Arrangement.SpaceEvenly
//    ) {
//        items(items = weekDays) {
//            Text(text = it, style = MaterialTheme.typography.bodySmall)
//        }
//    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RemindersCalendarDays(
    reminders: List<Reminder>
) {
    LazyVerticalGrid(
        modifier = Modifier,
        columns = GridCells.Fixed(7),
        contentPadding = PaddingValues(horizontal = 18.dp)
    ) {
        val weekDays = listOf("пн", "вт", "ср", "чт", "пт", "сб", "вс")
        items(weekDays) {
            Text(
                text = it,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }

        items(30) {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Badge(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                    Text(text = it.toString())
                }
            }
        }
    }
}

@Composable
fun RemindersCalendar(
    modifier: Modifier = Modifier,
    calendarState: ReminderCalendarState = rememberReminderCalendarState()
) {
    val calendar = Calendar.getInstance(calendarState.zone, calendarState.locale)
    val localDate = LocalDate.now()

    calendar.set(localDate.year, localDate.month.value, localDate.dayOfMonth)

    val monthName = localDate.month.getDisplayName(
        TextStyle.FULL_STANDALONE, calendarState.locale
    ).replaceFirstChar { it.uppercaseChar() }

    val names = calendar.getDisplayNames(Calendar.DAY_OF_WEEK, Calendar.SHORT_STANDALONE, calendarState.locale)
    if (names != null) {
        Log.e("NAMES", names.toString())
    }

    val monthReminders = calendarState.reminders.filter {
        LocalDate.ofEpochDay(it.timestamp).monthValue == localDate.monthValue
    }

    Column(
        modifier = modifier
    ) {
        RemindersCalendarHeader(monthName)
        RemindersCalendarWeekdayTitles()
        RemindersCalendarDays(calendarState.reminders)
    }
}

@Preview(showBackground = true)
@Composable
fun RemindersCalendarPreview() {
    RemingoTheme {
        RemindersCalendar()
    }
}