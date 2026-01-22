package com.example.cofeebreak.feature_app.domain.usecase

import com.example.cofeebreak.feature_app.domain.model.Profile
import com.example.cofeebreak.feature_app.domain.repository.ProfileRepository

class GetUserNameUseCase(private val profileRepository: ProfileRepository) {
    suspend operator fun invoke(id: String): Profile{
        return profileRepository.getUserName(id)
    }
}