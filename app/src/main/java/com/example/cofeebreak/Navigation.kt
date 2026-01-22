package com.example.cofeebreak

import com.example.cofeebreak.feature_app.domain.model.Coffee
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

    @Serializable
    data object ResetPasswordScreen: Navigation()

    @Serializable
    data object CafeScreen: Navigation()

    @Serializable
    data object RewardScreen: Navigation()

    @Serializable
    data object MyOrderScreen: Navigation()

    @Serializable
    data object ProfileScreen: Navigation()

    @Serializable
    data object RedeemScreen: Navigation()

    @Serializable
    data class OrderOptionsScreen(val imageUrl: String): Navigation()
}