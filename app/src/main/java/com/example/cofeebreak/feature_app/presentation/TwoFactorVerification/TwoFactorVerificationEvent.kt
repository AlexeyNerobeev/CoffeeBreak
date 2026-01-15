package com.example.cofeebreak.feature_app.presentation.TwoFactorVerification

sealed class TwoFactorVerificationEvent {
    data class EnteredFirstNumber(val value: String): TwoFactorVerificationEvent()
    data class EnteredSecondNumber(val value: String): TwoFactorVerificationEvent()
    data class EnteredThirdNumber(val value: String): TwoFactorVerificationEvent()
    data class EnteredFourthNumber(val value: String): TwoFactorVerificationEvent()
}