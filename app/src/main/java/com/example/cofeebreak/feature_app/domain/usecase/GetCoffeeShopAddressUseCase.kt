package com.example.cofeebreak.feature_app.domain.usecase

import com.example.cofeebreak.feature_app.domain.model.Profile
import com.example.cofeebreak.feature_app.domain.repository.ProfileRepository

class GetCoffeeShopAddressUseCase(private val profileRepository: ProfileRepository) {
    suspend operator fun invoke(id: Profile): Profile{
        return profileRepository.getCoffeeShopAddress(id)
    }
}