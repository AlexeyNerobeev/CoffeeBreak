package com.example.cofeebreak.feature_app.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Profile (
    val id: Int = 0,
    val user_id: String = "",
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val coffee_shop_address: String? = "",
    val avatar_url: String? = null
)