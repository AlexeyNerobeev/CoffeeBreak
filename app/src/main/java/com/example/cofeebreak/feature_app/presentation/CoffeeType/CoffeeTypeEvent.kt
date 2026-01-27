package com.example.cofeebreak.feature_app.presentation.CoffeeType

sealed class CoffeeTypeEvent {
    data object ChangeError: CoffeeTypeEvent()
}