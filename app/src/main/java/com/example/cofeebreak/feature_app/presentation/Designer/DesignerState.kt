package com.example.cofeebreak.feature_app.presentation.Designer

import com.example.cofeebreak.feature_app.domain.model.Order

data class DesignerState(
    val sliderPosition: Float = 0.5f,
    val selectMilk: Boolean = false,
    val selectSyrup: Boolean = false,
    val description: String = "",
    val showDescription: Boolean = false,
    val order: Order = Order()
)