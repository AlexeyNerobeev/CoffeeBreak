package com.example.cofeebreak.feature_app.data.repositoryImplementation

import com.example.cofeebreak.feature_app.data.supabase.Connect.supabase
import com.example.cofeebreak.feature_app.domain.model.Additives
import com.example.cofeebreak.feature_app.domain.repository.AdditivesRepository
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class AdditivesRepositoryImpl: AdditivesRepository {
    override suspend fun getAdditives(): List<Additives> {
        return supabase.postgrest["additives"].select(
            columns = Columns.ALL
        ).decodeList<Additives>()
    }
}