package com.example.cofeebreak.feature_app.domain.repository

interface ProfileRepository {
    suspend fun createProfile(name: String, phone: String)
}