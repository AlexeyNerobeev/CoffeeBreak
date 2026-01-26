package com.example.cofeebreak.feature_app.presentation.CoffeeCountry

sealed class CoffeeCountryEvent {
    data object ChangeError: CoffeeCountryEvent()
}