package com.example.cofeebreak.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val id: Int = 0,
    val created_at: String = "",
    val name: String = "",
    val time_to: String = "",
    val price: Int = 0,
    val coffee_image: String = "",
    val options: String = "",
    val count: Int = 0,
    val user_id: String = "",
    val status: String = "",
    val ristretto: Int = 0,
    val volume: Int = 0,
    val barista: Int = 0,
    val coffee_type: Int = 0,
    val milk: String = "",
    val syrup: String = "",
    val additives: Int = 0
)
