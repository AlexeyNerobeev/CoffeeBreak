package com.example.cofeebreak.feature_app.domain.usecase

import com.example.cofeebreak.feature_app.domain.repository.ProfileRepository

class UpdateAvatarUrlUseCase(private val profileRepository: ProfileRepository) {
    suspend operator fun invoke(userId: String, avatarUrl: String){
        profileRepository.updateAvatarUrl(userId = userId, avatarUrl = avatarUrl)
    }
}