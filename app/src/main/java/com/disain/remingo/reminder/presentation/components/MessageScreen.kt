package com.disain.remingo.reminder.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.disain.remingo.R
import com.example.compose.RemingoTheme

@Composable
fun EmptyRemindersMessage(
    modifier: Modifier = Modifier
) {
    MessageScreen(
        modifier = modifier,
        image = R.drawable.compass,
        title = stringResource(id = R.string.empty_reminders_message_title),
        description = stringResource(id = R.string.empty_reminders_message_description)
    )
}

@Composable
fun MessageScreen(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int? = null,
    title: String,
    description: String? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        image?.let {
            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(id = image),
                contentDescription = null
            )
        }

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )

        description?.let {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }

        Row(
            modifier = Modifier.padding(top = 12.dp),
            content = actions
        )
    }
}

@Preview
@Composable
fun MessageScreenPreview() {
    RemingoTheme {
        MessageScreen(
            modifier = Modifier.fillMaxSize(),
            image = R.drawable.compass,
            title = "Ничего не забыто!",
            description = "Ваши напоминания пока пусты. Добавьте новые задачи и события, чтобы быть всегда в курсе дел!"
        )
    }
}