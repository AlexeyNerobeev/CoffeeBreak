package com.example.cofeebreak.feature_app.domain.repository

import com.example.cofeebreak.feature_app.domain.model.CoffeeCountry

interface CoffeeCountryRepository {
    suspend fun getCoffeeCountry(): List<CoffeeCountry>
}