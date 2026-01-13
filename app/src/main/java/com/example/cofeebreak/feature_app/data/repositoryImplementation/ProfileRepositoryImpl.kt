package com.example.cofeebreak.feature_app.data.repositoryImplementation

import com.example.cofeebreak.feature_app.data.supabase.Connect.supabase
import com.example.cofeebreak.feature_app.domain.model.Profile
import com.example.cofeebreak.feature_app.domain.repository.ProfileRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest

class ProfileRepositoryImpl: ProfileRepository {
    override suspend fun createProfile(
        name: String,
        phone: String
    ) {
        val profile = Profile(name = name, phone = phone, email = getCurrentUserEmail(), user_id = getCurrentUserId())
        supabase.postgrest["profile"].insert(profile)
    }

    suspend fun getCurrentUserId(): String{
        supabase.auth.awaitInitialization()
        return supabase.auth.currentUserOrNull()?.id?:""
    }

    suspend fun getCurrentUserEmail(): String{
        supabase.auth.awaitInitialization()
        return supabase.auth.currentUserOrNull()?.email?:""
    }
}