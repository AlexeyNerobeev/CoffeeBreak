package com.example.cofeebreak.feature_app.presentation.OrderOptions

import com.example.cofeebreak.feature_app.domain.model.Coffee

data class OrderOptionsState(
    val coffeeCount: Int = 1,
    val switch: Boolean = false,
    val coffee: Coffee = Coffee(),
    val price: Int = 0,
    val load: Boolean = true,
    val totalPrice: Int = 0,
    val ristretto: Int = 0,
    val volume: Int = 250,
    val isComplete: Boolean = false,
    val progressIndicator: Boolean = false,
    val orderId: Int = 0
)