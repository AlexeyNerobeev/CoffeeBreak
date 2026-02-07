package com.example.cofeebreak.feature_app.presentation.Cafe

import com.example.cofeebreak.feature_app.domain.model.CoffeeShop

data class CafeState(
    val coffeeShopList: List<CoffeeShop> = listOf(),
    val load: Boolean = true,
    val isComplete: Boolean = false
)