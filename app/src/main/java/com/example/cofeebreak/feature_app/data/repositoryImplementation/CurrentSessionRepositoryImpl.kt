package com.example.cofeebreak.feature_app.data.repositoryImplementation

import android.content.Context
import androidx.core.content.edit
import com.example.cofeebreak.feature_app.domain.model.User
import com.example.cofeebreak.feature_app.domain.repository.CurrentSessionRepository

class CurrentSessionRepositoryImpl(context: Context): CurrentSessionRepository {
    val sharedPrefs = context.getSharedPreferences("CurrentUser", Context.MODE_PRIVATE)

    override suspend fun saveCurrentUserId(id: User) {
        sharedPrefs.edit { putString("userId", id.id) }
    }

    override suspend fun loadCurrentUseId(): User {
        val id = sharedPrefs.getString("userId", null)
        return User(id = id)
    }

    override suspend fun deleteCurrentUserId() {
        sharedPrefs.edit().clear().apply()
    }
}