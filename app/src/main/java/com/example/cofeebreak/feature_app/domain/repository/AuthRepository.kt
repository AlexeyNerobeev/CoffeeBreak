package com.example.cofeebreak.feature_app.domain.repository

import com.example.cofeebreak.feature_app.domain.model.User

interface AuthRepository {
    suspend fun signIn(inputEmail: String, inputPassword: String)
    suspend fun signUp(inputEmail: String, inputPassword: String)
    suspend fun getCurrentUserId(): User
}