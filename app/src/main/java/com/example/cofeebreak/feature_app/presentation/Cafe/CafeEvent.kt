package com.example.cofeebreak.feature_app.presentation.Cafe

sealed class CafeEvent {
    data class SaveCoffeeShopAddress(val value: String): CafeEvent()
}