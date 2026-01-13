package com.example.cofeebreak.feature_app.presentation.Authorization

sealed class AuthorizationEvent {
    data class EnteredEmail(val value: String): AuthorizationEvent()
    data class EnteredPassword(val value: String): AuthorizationEvent()
    data object SignIn: AuthorizationEvent()
    data object PasswordVisible: AuthorizationEvent()
}