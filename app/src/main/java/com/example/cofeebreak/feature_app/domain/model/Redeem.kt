package com.example.cofeebreak.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Redeem(
    val id: Int = 0,
    val name: String = "",
    val really_up_to: String = "",
    val price: Int = 0,
    val coffee_image: String = ""
)