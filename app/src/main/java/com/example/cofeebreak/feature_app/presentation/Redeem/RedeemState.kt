package com.example.cofeebreak.feature_app.presentation.Redeem

import com.example.cofeebreak.feature_app.domain.model.Redeem

data class RedeemState(
    val serverError: Boolean = false,
    val redeemList: List<Redeem> = listOf(),
    val isLoaded: Boolean = false
)