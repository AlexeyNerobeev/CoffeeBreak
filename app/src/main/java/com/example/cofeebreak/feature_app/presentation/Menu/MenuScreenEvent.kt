package com.example.cofeebreak.feature_app.presentation.Menu

sealed class MenuScreenEvent {
    data object GetUserName: MenuScreenEvent()
    data object GetDrinks: MenuScreenEvent()
}