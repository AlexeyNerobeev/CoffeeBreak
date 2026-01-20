package com.example.cofeebreak.feature_app.domain.usecase

import com.example.cofeebreak.feature_app.domain.repository.ProfileRepository
import javax.inject.Inject

class CreateProfileUseCase(private val profileRepository: ProfileRepository) {
    suspend operator fun invoke(name: String, phone: String) =
        profileRepository.createProfile(name = name, phone = phone)

}