package com.example.cofeebreak.feature_app.data.dto

import com.example.cofeebreak.feature_app.data.supabase.Connect.supabase
import com.example.cofeebreak.feature_app.domain.model.Dobavki
import com.example.cofeebreak.feature_app.domain.model.DobavkiRepository
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.Serializable

@Serializable
data class AdditivesDto(override val id: String, override val name: String): Dobavki

class DobavkiRepositoryImpl: DobavkiRepository {
    override suspend fun getDobavki(): Dobavki {
        return supabase.postgrest["additives"].select().decodeSingle<AdditivesDto>()
    }
}