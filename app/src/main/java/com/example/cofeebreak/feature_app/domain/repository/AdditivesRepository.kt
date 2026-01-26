package com.example.cofeebreak.feature_app.domain.repository

import com.example.cofeebreak.feature_app.domain.model.Additives

interface AdditivesRepository {
    suspend fun getAdditives(): List<Additives>
}