package com.example.cofeebreak.feature_app.presentation.ForgotPassword

sealed class ForgotPasswordEvent {
    data class EnteredEmail(val value: String): ForgotPasswordEvent()
    data object ValidEmail: ForgotPasswordEvent()
    data object ChangeError: ForgotPasswordEvent()
    data object ErrorValidEmail: ForgotPasswordEvent()
}