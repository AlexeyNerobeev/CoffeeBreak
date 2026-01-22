package com.example.cofeebreak.feature_app.presentation.Menu

import com.example.cofeebreak.feature_app.domain.model.Coffee

data class MenuScreenState (
    val name: String = "",
    val coffeeList: List<Coffee> = listOf(),
    val serverError: Boolean = false
)