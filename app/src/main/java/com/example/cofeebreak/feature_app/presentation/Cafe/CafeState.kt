package com.example.cofeebreak.feature_app.presentation.Cafe

import com.example.cofeebreak.feature_app.domain.model.CoffeeShop
import com.yandex.mapkit.geometry.Point

data class CafeState(
    val coffeeShopList: List<CoffeeShop> = listOf(),
    val coffeePoints: List<Point> = listOf(),
    val userLocation: Point? = null,
    val selectedCoffeeIndex: Int? = null,
    val load: Boolean = true,
    val isComplete: Boolean = false
)