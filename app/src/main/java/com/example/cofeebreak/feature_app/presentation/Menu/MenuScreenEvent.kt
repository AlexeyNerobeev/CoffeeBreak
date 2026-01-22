package com.example.cofeebreak.feature_app.presentation.Menu

sealed class MenuScreenEvent {
    data object ChangeError: MenuScreenEvent()
}