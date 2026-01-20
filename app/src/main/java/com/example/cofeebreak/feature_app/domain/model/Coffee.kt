package com.example.cofeebreak.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Coffee(
    val id: Int = 0,
    val coffee_name: String = "",
    val price: Int = 0,
    val coffee_image: String = ""
)