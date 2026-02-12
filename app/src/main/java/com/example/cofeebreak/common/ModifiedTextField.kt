package com.example.cofeebreak.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cofeebreak.R
import com.example.cofeebreak.ui.theme.Theme

@Composable
fun ModifiedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    testTag: String,
    leadingIcon: Int,
    placeholder: String,
    trailingIcon: Boolean,
    passwordTransformation: Boolean,
    passwordVisible: () -> Unit,
    padding: Int
) {
    TextField(
        value = value,
        singleLine = true,
        onValueChange = {
            onValueChange(it)
        },
        modifier = Modifier
            .padding(top = padding.dp)
            .fillMaxWidth()
            .testTag(testTag),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent.copy(alpha = 0.1f),
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = colorResource(R.color.TfColor),
            unfocusedIndicatorColor = colorResource(R.color.TfColor),
            focusedTextColor = Theme.colors.oppositeColor,
            unfocusedTextColor = Theme.colors.oppositeColor
        ),
        leadingIcon = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(leadingIcon),
                    contentDescription = null,
                    tint = Theme.colors.tfIconsColor
                )
                Box(
                    modifier = Modifier
                        .padding(start = 9.dp)
                        .background(colorResource(R.color.TfColor))
                        .clip(RectangleShape)
                        .height(25.dp)
                        .width(1.dp)
                )
            }
        },
        placeholder = {
            Text(
                text = placeholder,
                fontFamily = roboto,
                fontSize = 12.sp,
                color = colorResource(R.color.PlaceholderColor)
            )
        },
        trailingIcon = {
            if (trailingIcon){
                IconButton(
                    onClick = {
                        passwordVisible()
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.eye_icon),
                        contentDescription = null,
                        tint = Theme.colors.eyeIconColor
                    )
                    if (!passwordTransformation) {
                        Icon(
                            painterResource(R.drawable.line_eye_icon),
                            contentDescription = null,
                            tint = Theme.colors.eyeIconColor
                        )
                    }
                }
            }
        },
        visualTransformation = if (trailingIcon){
            if(passwordTransformation)
                PasswordVisualTransformation('*')
            else
                VisualTransformation.None
        } else{
            VisualTransformation.None
        }
    )
}