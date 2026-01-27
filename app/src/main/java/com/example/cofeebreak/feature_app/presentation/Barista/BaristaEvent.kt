package com.example.cofeebreak.feature_app.presentation.Barista

sealed class BaristaEvent {
    data object ChangeError: BaristaEvent()
}