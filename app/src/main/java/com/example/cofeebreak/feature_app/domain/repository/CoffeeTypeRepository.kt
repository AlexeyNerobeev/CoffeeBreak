package com.example.cofeebreak.feature_app.domain.repository

import com.example.cofeebreak.feature_app.domain.model.CoffeeType

interface CoffeeTypeRepository{
    suspend fun getCoffeeType(): List<CoffeeType>
}
