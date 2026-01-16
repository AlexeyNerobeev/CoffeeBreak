package com.example.cofeebreak.feature_app.presentation.ResetPassword

sealed class ResetPasswordEvent {
    data class EnteredPassword(val value: String): ResetPasswordEvent()
    data object ChangeEmptyFieldError: ResetPasswordEvent()
    data object IsPasswordStrong: ResetPasswordEvent()
    data object PasswordVisible: ResetPasswordEvent()
    data object ChangePasswordError: ResetPasswordEvent()
}