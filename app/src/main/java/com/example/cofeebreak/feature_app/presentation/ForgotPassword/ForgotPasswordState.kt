package com.example.cofeebreak.feature_app.presentation.ForgotPassword

data class ForgotPasswordState(
    val email: String = "",
    val validEmail: Boolean? = null,
    val error: Boolean = false,
    val errorValidEmail: Boolean = false
)