package com.example.cofeebreak.feature_app.presentation.Authorization

data class AuthorizationState(
    val email: String = "",
    val password: String = "",
    val isComplete: Boolean = false,
    val passwordVisible: Boolean = false,
    val error: Boolean = false,
    val progressIndicator: Boolean = false
)