package com.example.cofeebreak.feature_app.presentation.Redeem

sealed class RedeemEvent {
    data object ChangeError: RedeemEvent()
}