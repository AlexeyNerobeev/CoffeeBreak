package com.example.cofeebreak.feature_app.domain.repository

import com.example.cofeebreak.feature_app.domain.model.Barista

interface BaristaRepository {
    suspend fun getBarista(): List<Barista>
}