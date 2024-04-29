package com.disain.remingo.reminder.presentation.reminders

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.disain.remingo.R
import com.disain.remingo.reminder.presentation.components.RemindersCalendar
import com.disain.remingo.reminder.presentation.components.RemindersTopBar
import com.disain.remingo.reminder.presentation.components.rememberReminderCalendarState
import com.disain.remingo.reminder.presentation.edit_reminder.EditReminderScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemindersScreen(
    navController: NavController,
    onEvent: (RemindersScreenEvent) -> Unit,
    uiState: ReminderUiState,
) {
    val listState = rememberLazyListState()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    var openedCalendar by remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(skipPartiallyExpanded = true)
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            RemindersTopBar(scaffoldState = scaffoldState, scrollBehavior = scrollBehavior)
        },

        sheetContent = {
            val addMessage = stringResource(id = R.string.reminder_added_message)

            EditReminderScreen(
                reminder = uiState.editReminder,
                onSaveButtonClick = {
                    scope.launch {
                        scaffoldState.bottomSheetState.hide()
                        scaffoldState.snackbarHostState.showSnackbar(addMessage)
                    }
                    onEvent(RemindersScreenEvent.addReminder(it))
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it.calculateTopPadding())
                .fillMaxSize()
        ) {
            RemindersCalendar(
                calendarState = rememberReminderCalendarState(reminders = uiState.reminders)
            )

//            if (uiState.reminders.isNotEmpty()) {
//                val deleteMessage = stringResource(id = R.string.reminder_deleted_message)
//
//                RemindersList(
//                    modifier = Modifier.fillMaxSize(),
//                    listState = listState,
//                    reminders = uiState.reminders,
//                    onDisableButtonClick = { reminder ->
//                        onEvent(
//                            RemindersScreenEvent.addReminder(
//                                reminder.copy(
//                                    enabled = !reminder.enabled
//                                )
//                            )
//                        )
//                    },
//                    onReminderClick = {
////                        onEvent(RemindersScreenEvent.editReminder(it))
////                        scope.launch {
////                            scaffoldState.bottomSheetState.expand()
////                        }
//                    },
//                    onDeleteButtonClick = {
//                        onEvent(RemindersScreenEvent.deleteReminder(it))
//
//                        scope.launch {
//                            scaffoldState.snackbarHostState.showSnackbar(deleteMessage)
//                        }
//                    }
//                )
//            } else {
//                EmptyRemindersMessage(
//                    modifier = Modifier.align(Alignment.Center),
//                    onAddButtonClick = {
//                        scope.launch {
//                            scaffoldState.bottomSheetState.expand()
//                        }
//                    }
//                )
//            }
//
//            if (uiState.isLoading) {
//                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//            }
//        }
        }
    }
}