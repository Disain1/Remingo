package com.disain.remingo.reminder.presentation.edit_reminder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlarm
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.disain.remingo.R
import com.disain.remingo.reminder.data.local.Reminder

@Composable
fun EditReminderScreen(
    modifier: Modifier = Modifier,
    reminder: Reminder? = null,
    onSaveButtonClick: (Reminder) -> Unit,
) {
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val canSaveReminder by remember {
        derivedStateOf {
            title.isNotEmpty()
        }
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.AddAlarm, contentDescription = null)
                }
            })
        }
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .padding(it),
        ) {
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    enabled = canSaveReminder,
                    onClick = {
                        onSaveButtonClick(
                            reminder?.copy(
                                name = title,
                                description = description
                            )
                                ?: Reminder(
                                    id = 0,
                                    name = title,
                                    description = description,
                                    timestamp = System.currentTimeMillis(),
                                    enabled = true,
                                    color = 100L
                                )
                        )

                        title = ""
                        description = ""
                    }
                ) {
                    Icon(imageVector = Icons.Default.Done, contentDescription = null)
                }
            }

            BasicTextField(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                value = title,
                singleLine = true,
                onValueChange = {
                    title = it
                },
                textStyle = MaterialTheme.typography.titleLarge,
                decorationBox = { innerTextField ->
                    Box {
                        if (title.isEmpty()) {
                            Text(
                                text = stringResource(id = R.string.reminder_title_hint),
                                modifier = Modifier.alpha(.5f),
                                style = MaterialTheme.typography.titleLarge
                            )
                        }

                        innerTextField()
                    }
                }
            )

            BasicTextField(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxSize(),
                value = description,
                onValueChange = {
                    description = it
                },
                textStyle = MaterialTheme.typography.bodyMedium,
                decorationBox = { innerTextField ->
                    Box {
                        if (description.isEmpty()) {
                            Text(
                                text = stringResource(id = R.string.reminder_description_hint),
                                modifier = Modifier.alpha(.5f),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        innerTextField()
                    }
                }
            )
        }
    }
}