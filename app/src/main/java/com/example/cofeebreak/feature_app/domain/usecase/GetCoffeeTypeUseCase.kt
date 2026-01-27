package com.example.cofeebreak.feature_app.domain.usecase

import com.example.cofeebreak.feature_app.domain.model.CoffeeType
import com.example.cofeebreak.feature_app.domain.repository.CoffeeTypeRepository

class GetCoffeeTypeUseCase(private val coffeeTypeRepository: CoffeeTypeRepository) {
    suspend operator fun invoke(): List<CoffeeType>{
        return coffeeTypeRepository.getCoffeeType()
    }
}