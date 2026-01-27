package com.example.cofeebreak.feature_app.presentation.CoffeeType

import com.example.cofeebreak.feature_app.domain.model.CoffeeType

data class CoffeeTypeState(
    val coffeeTypeList: List<CoffeeType> = listOf(),
    val error: Boolean = false
)