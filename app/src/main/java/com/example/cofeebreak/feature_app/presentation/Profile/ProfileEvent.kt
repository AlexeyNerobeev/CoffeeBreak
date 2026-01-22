package com.example.cofeebreak.feature_app.presentation.Profile

sealed class ProfileEvent {
    data object ChangeError: ProfileEvent()
    data object QRVisibleChange: ProfileEvent()
}