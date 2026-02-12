package com.example.cofeebreak.feature_app.presentation.OrderOptions

import com.example.cofeebreak.feature_app.domain.model.Order

sealed class OrderOptionsEvent {
    data object MinusCoffeeCount: OrderOptionsEvent()
    data object PlusCoffeeCount: OrderOptionsEvent()
    data object Switch: OrderOptionsEvent()
    data class SelectRistretto(val value: Int): OrderOptionsEvent()
    data class SelectVolume(val value: Int): OrderOptionsEvent()
    data object SaveOrder: OrderOptionsEvent()
    data object ProgressIndicator: OrderOptionsEvent()
    data object TimeInputChange: OrderOptionsEvent()
    data class TimeSelect(val hour: Int, val minute: Int): OrderOptionsEvent()
}