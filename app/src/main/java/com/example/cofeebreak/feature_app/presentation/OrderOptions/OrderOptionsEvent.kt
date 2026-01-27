package com.example.cofeebreak.feature_app.presentation.OrderOptions

sealed class OrderOptionsEvent {
    data object MinusCoffeeCount: OrderOptionsEvent()
    data object PlusCoffeeCount: OrderOptionsEvent()
    data object Switch: OrderOptionsEvent()
}