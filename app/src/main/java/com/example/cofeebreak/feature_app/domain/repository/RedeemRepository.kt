package com.example.cofeebreak.feature_app.domain.repository

import com.example.cofeebreak.feature_app.domain.model.Redeem

interface RedeemRepository {
    suspend fun getRedeem(): List<Redeem>
}