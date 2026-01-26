package com.example.cofeebreak.feature_app.presentation.CoffeeCountry

import com.example.cofeebreak.feature_app.domain.model.CoffeeCountry

data class CoffeeCountryState(
    val coffeeCountryList: List<CoffeeCountry> = listOf(),
    val error: Boolean = false
)