package com.example.cofeebreak.feature_app.domain.repository

import com.example.cofeebreak.feature_app.domain.model.Coffee

interface CoffeeRepository {
    suspend fun getCoffeeList(): List<Coffee>
}