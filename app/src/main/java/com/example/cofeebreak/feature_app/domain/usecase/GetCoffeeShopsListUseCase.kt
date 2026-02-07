package com.example.cofeebreak.feature_app.domain.usecase

import com.example.cofeebreak.feature_app.domain.model.CoffeeShop
import com.example.cofeebreak.feature_app.domain.repository.CoffeeShopRepository

class GetCoffeeShopsListUseCase(private val coffeeShopRepository: CoffeeShopRepository) {
    suspend operator fun invoke(): List<CoffeeShop>{
        return coffeeShopRepository.getCoffeeShopsList()
    }
}