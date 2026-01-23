package com.example.cofeebreak.feature_app.domain.usecase

import com.example.cofeebreak.feature_app.domain.repository.ProfileRepository

class UploadAvatarUseCase(private val profileRepository: ProfileRepository) {
    suspend operator fun invoke(userId: String, bytes: ByteArray): String{
        return profileRepository.uploadAvatar(userId = userId, bytes = bytes)
    }
}