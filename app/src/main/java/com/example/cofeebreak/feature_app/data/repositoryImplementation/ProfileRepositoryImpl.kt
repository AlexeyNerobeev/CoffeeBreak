package com.example.cofeebreak.feature_app.data.repositoryImplementation

import com.example.cofeebreak.feature_app.data.supabase.Connect.supabase
import com.example.cofeebreak.feature_app.domain.model.Profile
import com.example.cofeebreak.feature_app.domain.repository.ProfileRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(): ProfileRepository {
    override suspend fun createProfile(
        name: String,
        phone: String
    ) {
        val profile = Profile(name = name, phone = phone, email = getCurrentUserEmail(), user_id = getCurrentUserId())
        supabase.postgrest["profile"].insert(profile)
    }

    override suspend fun getUserName(id: String): Profile {
        return supabase.postgrest["profile"].select(
            columns = Columns.list(
                "name"
            )
        ){
            filter {
                and {
                    eq("user_id", id)
                }
            }
        }.decodeSingle<Profile>()
    }

    override suspend fun getProfile(id: Profile): Profile {
        return supabase.postgrest["profile"].select(
            columns = Columns.list(
                "id",
                "name",
                "phone",
                "email",
                "coffee_shop_address"
            )
        ){
            filter {
                and {
                    eq("user_id", id.user_id)
                }
            }
        }.decodeSingle<Profile>()
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