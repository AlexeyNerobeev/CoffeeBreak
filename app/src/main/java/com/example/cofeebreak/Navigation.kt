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
    data class MenuScreen(val value: Boolean): Navigation()

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
    data class OrderOptionsScreen(val coffeeId: Int): Navigation()

    @Serializable
    data class DesignerScreen(val orderId: Int): Navigation()

    @Serializable
    data object BaristaScreen: Navigation()

    @Serializable
    data object AdditivesScreen: Navigation()

    @Serializable
    data object CoffeeCountryScreen: Navigation()

    @Serializable
    data object CoffeeTypeScreen: Navigation()

    @Serializable
    data object OrderIsConfirmedScreen: Navigation()

    @Serializable
    data object MyOrderCurrentScreen: Navigation()
}