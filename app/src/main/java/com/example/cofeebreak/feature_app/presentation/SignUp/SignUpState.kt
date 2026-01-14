    package com.example.cofeebreak.feature_app.presentation.SignUp

    data class SignUpState(
        val name: String = "",
        val phone: String = "",
        val emailAddress: String = "",
        val password: String = "",
        val isComplete: Boolean = false,
        val passwordVisible: Boolean = false,
        val error: Boolean = false,
        val fieldsEmpty: Boolean = false,
        val passwordError: Boolean = false,
        val progressIndicator: Boolean = false
    )