package com.example.cofeebreak.feature_app.domain.repository

import com.example.cofeebreak.feature_app.domain.model.CoffeeShop

interface CoffeeShopRepository {
    suspend fun getCoffeeShopsList(): List<CoffeeShop>
}