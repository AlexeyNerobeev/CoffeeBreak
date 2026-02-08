package com.example.cofeebreak.feature_app.presentation.Cafe

import android.content.Context
import com.yandex.mapkit.geometry.Point

sealed class CafeEvent {
    data class SaveCoffeeShopAddress(val value: String): CafeEvent()
    data class OnUserLocationReceived(val point: Point) : CafeEvent()
}