package com.example.cofeebreak.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User (
    val id: String? = "",
    val email: String = "",
    val password: String = ""
)