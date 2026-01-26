package com.example.cofeebreak.feature_app.data.repositoryImplementation

import com.example.cofeebreak.feature_app.data.supabase.Connect.supabase
import com.example.cofeebreak.feature_app.domain.model.CoffeeType
import com.example.cofeebreak.feature_app.domain.repository.CoffeeTypeRepository
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class CoffeeTypeRepositoryImpl: CoffeeTypeRepository {
    override suspend fun getCoffeeType(): List<CoffeeType> {
        return supabase.postgrest["coffee_type"].select(
            columns = Columns.ALL
        ).decodeList<CoffeeType>()
    }
}