package com.example.cofeebreak.feature_app.data.repositoryImplementation

import com.example.cofeebreak.feature_app.data.supabase.Connect.supabase
import com.example.cofeebreak.feature_app.domain.model.Profile
import com.example.cofeebreak.feature_app.domain.repository.ProfileRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.storage.storage
import kotlinx.serialization.json.jsonPrimitive
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
                "coffee_shop_address",
                "avatar_url"
            )
        ){
            filter {
                and {
                    eq("user_id", id.user_id)
                }
            }
        }.decodeSingle<Profile>()
    }

    override suspend fun updateAvatarUrl(
        userId: String,
        avatarUrl: String
    ) {
        supabase.postgrest["profile"].update(
            {
                set("avatar_url", avatarUrl)
            }
        ) {
            filter {
                eq("user_id", userId)
            }
        }
    }


    override suspend fun uploadAvatar(
        userId: String,
        bytes: ByteArray
    ): String {

        val path = "$userId/avatar.jpg"

        supabase.storage["avatars"].upload(
            path = path,
            data = bytes
        ){
            upsert = true
        }

        return supabase.storage["avatars"].publicUrl(path)
    }

    override suspend fun getUserAvatar(id: Profile): Profile {
        return supabase.postgrest["profile"].select(
            columns = Columns.list(
                "avatar_url"
            )
        ){
            filter {
                and {
                    eq("user_id", id.user_id)
                }
            }
        }.decodeSingle<Profile>()
    }

    override suspend fun getCoffeeShopAddress(id: Profile): Profile {
        return supabase.postgrest["profile"].select(
            columns = Columns.list(
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

    override suspend fun checkAndCreateProfile(profile: Profile) {
        val exists = supabase.postgrest["profile"].select{
            filter {
                and {
                    eq("user_id", profile.user_id)
                }
            }
        }.decodeList<Profile>()
            .isNotEmpty()
        if(!exists){
            val newProfile = Profile(user_id = profile.user_id,
                name = getCurrentUserName(),
                email = getCurrentUserEmail(),
                phone = "-")
            supabase.postgrest["profile"].insert(newProfile)
        }
    }

    suspend fun getCurrentUserId(): String{
        supabase.auth.awaitInitialization()
        return supabase.auth.currentUserOrNull()?.id?:""
    }

    suspend fun getCurrentUserEmail(): String{
        supabase.auth.awaitInitialization()
        return supabase.auth.currentUserOrNull()?.email?:""
    }

    suspend fun getCurrentUserName(): String{
        supabase.auth.awaitInitialization()
        return supabase.auth.currentUserOrNull()?.identities
            ?.firstOrNull()
            ?.identityData
            ?.get("name")
            ?.jsonPrimitive
            ?.content.toString()
    }
}