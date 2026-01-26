package com.example.cofeebreak.feature_app.domain.usecase

import com.example.cofeebreak.feature_app.domain.model.CoffeeCountry
import com.example.cofeebreak.feature_app.domain.repository.CoffeeCountryRepository

class GetCoffeeCountryUseCase(private val coffeeCountryRepository: CoffeeCountryRepository) {
    suspend operator fun invoke(): List<CoffeeCountry>{
        return coffeeCountryRepository.getCoffeeCountry()
    }
}