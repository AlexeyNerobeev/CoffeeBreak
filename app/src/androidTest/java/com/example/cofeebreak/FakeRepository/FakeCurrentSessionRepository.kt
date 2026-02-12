package com.example.cofeebreak.FakeRepository

import com.example.cofeebreak.feature_app.domain.model.User
import com.example.cofeebreak.feature_app.domain.repository.CurrentSessionRepository

class FakeCurrentSessionRepository: CurrentSessionRepository {
    override suspend fun saveCurrentUserId(id: User) {}
    override suspend fun loadCurrentUseId(): User {return User()}
    override suspend fun deleteCurrentUserId() {}
}