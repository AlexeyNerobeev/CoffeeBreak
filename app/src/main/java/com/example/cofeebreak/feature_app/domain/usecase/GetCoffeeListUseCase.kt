package com.example.cofeebreak.feature_app.domain.usecase

import com.example.cofeebreak.feature_app.domain.model.Coffee
import com.example.cofeebreak.feature_app.domain.repository.CoffeeRepository

class GetCoffeeListUseCase(private val coffeeRepository: CoffeeRepository) {
    suspend operator fun invoke(): List<Coffee>{
        return coffeeRepository.getCoffeeList()
    }
}