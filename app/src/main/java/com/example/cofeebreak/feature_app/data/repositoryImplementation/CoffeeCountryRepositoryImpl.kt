package com.example.cofeebreak.feature_app.data.repositoryImplementation

import com.example.cofeebreak.feature_app.data.supabase.Connect.supabase
import com.example.cofeebreak.feature_app.domain.model.CoffeeCountry
import com.example.cofeebreak.feature_app.domain.repository.CoffeeCountryRepository
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class CoffeeCountryRepositoryImpl: CoffeeCountryRepository {
    override suspend fun getCoffeeCountry(): List<CoffeeCountry> {
        return supabase.postgrest["coffee_country"].select(
            columns = Columns.ALL
        ).decodeList<CoffeeCountry>()
    }
}