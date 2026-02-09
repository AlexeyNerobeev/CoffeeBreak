package com.example.cofeebreak.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.cofeebreak.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertTimeInput(
    select: (hour: Int, minute: Int) -> Unit,
    dismiss: () -> Unit
) {
    val state = rememberTimePickerState(
        initialHour = 12,
        initialMinute = 0,
        is24Hour = true
    )
    AlertDialog(
        onDismissRequest = dismiss,
        text = {
            TimeInput(
                state = state
            )
        },
        title = {
            Text(text = stringResource(R.string.choose_a_time))
        },
        confirmButton = {
            Button(
                onClick = {
                    select(state.hour, state.minute)
                }
            ) {
                Text("OK")
            }
        }
    )
}