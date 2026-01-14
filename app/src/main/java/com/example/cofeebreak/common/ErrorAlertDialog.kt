package com.example.cofeebreak.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.cofeebreak.R
import com.example.cofeebreak.ui.theme.Theme

@Composable
fun ErrorAlertDialog(error: String,
                     click: () -> Unit) {
    AlertDialog(
        containerColor = Theme.colors.mainBackgroundColor,
        onDismissRequest = click,
        title = {
            Text(text = stringResource(R.string.error_alert),
                color = Theme.colors.oppositeColor,
                fontFamily = roboto,
                fontSize = 24.sp
            )
        },
        text = {
            Text(text = error,
                color = Theme.colors.oppositeColor,
                fontFamily = roboto,
                fontSize = 20.sp)
        },
        confirmButton = {
            Button(
                onClick = click,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Theme.colors.tfColor
                )
            ) {
                Text(text = "OK",
                    color = Theme.colors.oppositeColor,
                    fontSize = 14.sp,
                    fontFamily = roboto)
            }
        }
    )
}