package com.example.cofeebreak

import kotlinx.serialization.Serializable

sealed class Navigation {

    @Serializable
    data object WelcomeScreen: Navigation()

    @Serializable
    data object StartupScreen: Navigation()

    @Serializable
    data object AuthorizationScreen: Navigation()
}