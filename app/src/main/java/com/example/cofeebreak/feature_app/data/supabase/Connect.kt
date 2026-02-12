package com.example.cofeebreak.feature_app.data.supabase

import com.example.cofeebreak.BuildConfig
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object Connect {
    val supabase = createSupabaseClient(
        supabaseUrl = BuildConfig.SUPABASE_URL,
        supabaseKey = BuildConfig.SUPABASE_KEY
    ){
        install(Auth)
        install(Postgrest)
        install(Storage)
        install(ComposeAuth) {
            googleNativeLogin(BuildConfig.GOOGLE_SERVER_CLIENT_ID)
        }
    }
}