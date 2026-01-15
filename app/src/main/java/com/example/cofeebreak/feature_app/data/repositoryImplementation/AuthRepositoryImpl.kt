package com.example.cofeebreak.feature_app.data.repositoryImplementation

import com.example.cofeebreak.feature_app.data.supabase.Connect.supabase
import com.example.cofeebreak.feature_app.domain.model.User
import com.example.cofeebreak.feature_app.domain.repository.AuthRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email

class AuthRepositoryImpl: AuthRepository {
    override suspend fun signIn(inputEmail: String, inputPassword: String) {
        supabase.auth.signInWith(Email){
            email = inputEmail
            password = inputPassword
        }
    }

    override suspend fun signUp(
        inputEmail: String,
        inputPassword: String
    ) {
        supabase.auth.signUpWith(Email){
            email = inputEmail
            password = inputPassword
        }
    }

    override suspend fun getCurrentUserId(): User {
        supabase.auth.awaitInitialization()
        val id = supabase.auth.currentUserOrNull()?.id?:""
        return User(id = id)
    }
}