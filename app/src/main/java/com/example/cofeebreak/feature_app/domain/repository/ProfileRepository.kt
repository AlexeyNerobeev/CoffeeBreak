package com.example.cofeebreak.feature_app.domain.repository

import com.example.cofeebreak.feature_app.domain.model.Profile

interface ProfileRepository {
    suspend fun createProfile(name: String, phone: String)
    suspend fun getUserName(id: String): Profile
    suspend fun getProfile(id: Profile): Profile
}