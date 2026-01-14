package com.example.cofeebreak.di

import com.example.cofeebreak.feature_app.data.repositoryImplementation.CurrentSessionRepositoryImpl
import com.example.cofeebreak.feature_app.domain.repository.CurrentSessionRepository
import com.example.cofeebreak.feature_app.domain.usecase.DeleteCurrentUserIdUseCse
import com.example.cofeebreak.feature_app.domain.usecase.LoadCurrentUserIdUseCase
import com.example.cofeebreak.feature_app.domain.usecase.SaveCurrentUserIdUseCase
import org.koin.dsl.module

val moduleCurrentSession = module {
    single<CurrentSessionRepository> {
        CurrentSessionRepositoryImpl(get())
    }
    factory<SaveCurrentUserIdUseCase> {
        SaveCurrentUserIdUseCase(get())
    }
    factory<LoadCurrentUserIdUseCase> {
        LoadCurrentUserIdUseCase(get())
    }
    factory<DeleteCurrentUserIdUseCse> {
        DeleteCurrentUserIdUseCse(get())
    }
}