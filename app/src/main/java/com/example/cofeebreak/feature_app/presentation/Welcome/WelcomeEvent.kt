package com.example.cofeebreak.feature_app.presentation.Welcome

sealed class WelcomeEvent {
    data object LoadCurrentUserId: WelcomeEvent()
}