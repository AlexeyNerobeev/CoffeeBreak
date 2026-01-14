package com.example.cofeebreak.feature_app.domain.usecase

import com.example.cofeebreak.feature_app.domain.model.User
import com.example.cofeebreak.feature_app.domain.repository.AuthRepository

class GetCurrentUserIdUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): User {
        return authRepository.getCurrentUserId()
    }
}