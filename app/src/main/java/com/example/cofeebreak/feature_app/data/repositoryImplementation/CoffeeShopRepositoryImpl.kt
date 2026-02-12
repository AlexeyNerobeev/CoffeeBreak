package com.example.cofeebreak.feature_app.data.repositoryImplementation

import com.example.cofeebreak.feature_app.data.dto.CoffeeShopDto
import com.example.cofeebreak.feature_app.data.supabase.Connect.supabase
import com.example.cofeebreak.feature_app.domain.model.CoffeeShop
import com.example.cofeebreak.feature_app.domain.repository.CoffeeShopRepository
import io.github.jan.supabase.postgrest.postgrest

class CoffeeShopRepositoryImpl: CoffeeShopRepository {
    override suspend fun getCoffeeShopsList(): List<CoffeeShop> {
        return supabase.postgrest["coffee_shop"].select().decodeList<CoffeeShopDto>()
    }
}