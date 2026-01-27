package com.example.cofeebreak.feature_app.data.repositoryImplementation

import com.example.cofeebreak.feature_app.data.supabase.Connect.supabase
import com.example.cofeebreak.feature_app.domain.model.Barista
import com.example.cofeebreak.feature_app.domain.repository.BaristaRepository
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class BaristaRepositoryImpl: BaristaRepository {
    override suspend fun getBarista(): List<Barista> {
        return supabase.postgrest["barista"].select(
            columns = Columns.list(
                "id",
                "name",
                "status",
                "barista_image"
            )
        ).decodeList<Barista>()
    }
}