package com.example.cofeebreak.feature_app.domain.usecase

import com.example.cofeebreak.feature_app.domain.model.User
import com.example.cofeebreak.feature_app.domain.repository.CurrentSessionRepository

class SaveCurrentUserIdUseCase(private val currentSessionRepository: CurrentSessionRepository) {
    suspend operator fun invoke(id: User){
        currentSessionRepository.saveCurrentUserId(id)
    }
}