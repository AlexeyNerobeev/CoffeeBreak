package com.example.cofeebreak.feature_app.presentation.Additives

import com.example.cofeebreak.feature_app.domain.model.Additives

data class AdditivesState(
    val additivesList: List<Additives> = listOf(),
    val error: Boolean = false
)