package com.example.cofeebreak.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CoffeeCountry(
    val id: Int = 0,
    val country_image: String = "",
    val name: String = "",
    val description: String = ""
)
