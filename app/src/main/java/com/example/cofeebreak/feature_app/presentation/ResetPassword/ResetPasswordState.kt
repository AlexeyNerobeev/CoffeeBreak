package com.example.cofeebreak.feature_app.presentation.ResetPassword

data class ResetPasswordState (
    val password: String = "",
    val emptyFieldError: Boolean = false,
    val passwordError: Boolean = false,
    val passwordVisible: Boolean = false,
    val isPasswordStrong: Boolean = false
)