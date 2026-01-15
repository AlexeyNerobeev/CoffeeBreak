package com.example.cofeebreak.feature_app.domain.usecase

import com.example.cofeebreak.feature_app.domain.repository.CurrentSessionRepository

class DeleteCurrentUserIdUseCse(private val currentSessionRepository: CurrentSessionRepository) {
    suspend operator fun invoke(){
        currentSessionRepository.deleteCurrentUserId()
    }
}