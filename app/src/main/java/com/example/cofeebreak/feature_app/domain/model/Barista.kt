package com.example.cofeebreak.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Barista (
    val name: String = "",
    val status: String = "",
    val barista_image: String = ""
)