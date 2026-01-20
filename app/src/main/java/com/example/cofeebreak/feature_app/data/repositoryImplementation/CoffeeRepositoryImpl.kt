package com.example.cofeebreak.feature_app.data.repositoryImplementation

import com.example.cofeebreak.feature_app.data.supabase.Connect.supabase
import com.example.cofeebreak.feature_app.domain.model.Coffee
import com.example.cofeebreak.feature_app.domain.repository.CoffeeRepository
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class CoffeeRepositoryImpl: CoffeeRepository {
    override suspend fun getCoffeeList(): List<Coffee> {
        return supabase.postgrest["coffee"].select(
            columns = Columns.list(
                "id",
                "coffee_name",
                "price",
                "coffee_image"
            )
        ).decodeList<Coffee>()
    }
}