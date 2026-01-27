package com.example.cofeebreak.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CoffeeType(
    val id: Int = 0,
    val coffee_image: String = "",
    val name: String = "",
    val description: String = ""
)
