package com.example.cofeebreak.feature_app.presentation.SignUp

sealed class SignUpEvent {
    data class EnteredName(val value: String): SignUpEvent()
    data class EnteredPhone(val value: String): SignUpEvent()
    data class EnteredEmailAddress(val value: String): SignUpEvent()
    data class EnteredPassword(val value: String): SignUpEvent()
    data object PasswordVisible: SignUpEvent()
    data object SignUp: SignUpEvent()
}