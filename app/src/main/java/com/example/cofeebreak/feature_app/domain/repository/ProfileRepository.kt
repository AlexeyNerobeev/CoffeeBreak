package com.example.cofeebreak.feature_app.domain.repository

import com.example.cofeebreak.feature_app.domain.model.Profile

interface ProfileRepository {
    suspend fun createProfile(name: String, phone: String)
    suspend fun getUserName(id: String): Profile
    suspend fun getProfile(id: Profile): Profile
    suspend fun getUserAvatar(id: Profile): Profile
    suspend fun uploadAvatar(
        userId: String,
        bytes: ByteArray
    ): String

    suspend fun updateAvatarUrl(
        userId: String,
        avatarUrl: String
    )

    suspend fun getCoffeeShopAddress(id: Profile): Profile
}