package com.example.cofeebreak.feature_app.data.supabase

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object Connect {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://uqgudoizujedjaoyqvas.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVxZ3Vkb2l6dWplZGphb3lxdmFzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjgxODYxMTYsImV4cCI6MjA4Mzc2MjExNn0.6pWo5XHBleGOCyVJOMpfhABY_l9t2VmY255-4RDaomI"
    ){
        install(Auth)
        install(Postgrest)
    }
}

// client secret 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