package com.example.cofeebreak.feature_app.data.dto

import com.example.cofeebreak.feature_app.domain.model.CoffeeShop
import kotlinx.serialization.Serializable

@Serializable
data class CoffeeShopDto(
    override val id: Int,
    override val address: String,
    override val latitude: Float,
    override val longitude: Float
) : CoffeeShop