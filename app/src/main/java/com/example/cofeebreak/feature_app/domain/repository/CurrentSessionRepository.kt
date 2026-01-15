package com.example.cofeebreak.feature_app.domain.repository

import com.example.cofeebreak.feature_app.domain.model.User

interface CurrentSessionRepository {
    suspend fun saveCurrentUserId(id: User)
    suspend fun loadCurrentUseId(): User
    suspend fun deleteCurrentUserId()
}