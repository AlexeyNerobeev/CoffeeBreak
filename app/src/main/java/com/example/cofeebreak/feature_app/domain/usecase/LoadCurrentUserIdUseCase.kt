package com.example.cofeebreak.feature_app.domain.usecase

import com.example.cofeebreak.feature_app.domain.model.User
import com.example.cofeebreak.feature_app.domain.repository.CurrentSessionRepository

class LoadCurrentUserIdUseCase(private val currentSessionRepository: CurrentSessionRepository) {
    suspend operator fun invoke(): User {
        return currentSessionRepository.loadCurrentUseId()
    }
}