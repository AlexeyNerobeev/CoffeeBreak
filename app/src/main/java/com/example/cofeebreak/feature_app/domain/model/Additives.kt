package com.example.cofeebreak.feature_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Additives(
    val id: Int = 0,
    val additives_image: String = "",
    val title: String = "",
    val description: String = ""
)

interface Dobavki {
    val id: String
    val name: String
}

interface DobavkiRepository {
    suspend fun getDobavki() : Dobavki
}