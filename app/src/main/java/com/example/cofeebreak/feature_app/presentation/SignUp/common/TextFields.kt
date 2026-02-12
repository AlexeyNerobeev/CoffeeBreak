package com.example.cofeebreak.feature_app.presentation.SignUp.common

import android.text.method.PasswordTransformationMethod
import androidx.compose.ui.text.Placeholder

data class TextFields(
    val value: String,
    val onValueChange: (String) -> Unit,
    val testTag: String,
    val placeholder: String,
    val leadingIcon: Int,
    val trailingIcon: Boolean,
    val passwordTransformation: Boolean,
    val passwordVisible: () -> Unit,
    val padding: Int
)