package com.example.cofeebreak.feature_app.presentation.Barista

import com.example.cofeebreak.feature_app.domain.model.Barista

data class BaristaState(
    val baristaList: List<Barista> = listOf(),
    val error: Boolean = false
)