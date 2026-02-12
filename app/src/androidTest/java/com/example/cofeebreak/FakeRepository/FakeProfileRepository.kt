package com.example.cofeebreak.FakeRepository

import com.example.cofeebreak.feature_app.domain.model.Profile
import com.example.cofeebreak.feature_app.domain.repository.ProfileRepository

class FakeProfileRepository : ProfileRepository {
    override suspend fun createProfile(name: String, phone: String) {}
    override suspend fun getUserName(id: String): Profile = Profile()
    override suspend fun getProfile(id: Profile): Profile = Profile()
    override suspend fun getUserAvatar(id: Profile): Profile = Profile()
    override suspend fun uploadAvatar(userId: String, bytes: ByteArray): String = ""
    override suspend fun updateAvatarUrl(userId: String, avatarUrl: String) {}
    override suspend fun getCoffeeShopAddress(id: Profile): Profile = Profile()
    override suspend fun checkAndCreateProfile(profile: Profile) {}
}
