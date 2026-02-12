package com.example.cofeebreak.FakeRepository

import com.example.cofeebreak.feature_app.domain.model.User
import com.example.cofeebreak.feature_app.domain.repository.AuthRepository

class FakeAuthRepository : AuthRepository {
    override suspend fun signIn(inputEmail: String, inputPassword: String) {}
    override suspend fun signUp(inputEmail: String, inputPassword: String) {}
    override suspend fun getCurrentUserId(): User = User(id = "1")
    override suspend fun resetPassword(user: User) {}
}
