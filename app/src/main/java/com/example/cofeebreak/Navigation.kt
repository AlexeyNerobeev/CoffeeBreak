package com.example.cofeebreak

import kotlinx.serialization.Serializable

sealed class Navigation {

    @Serializable
    data object WelcomeScreen: Navigation()

    @Serializable
    data object StartupScreen: Navigation()

    @Serializable
    data object AuthorizationScreen: Navigation()

    @Serializable
    data object SignUpScreen: Navigation()

    @Serializable
    data object MenuScreen: Navigation()

    @Serializable
    data object ForgotPasswordScreen: Navigation()

    @Serializable
    data object TwoFactorVerificationScreen: Navigation()
}