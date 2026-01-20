package com.example.cofeebreak.di

import com.example.cofeebreak.feature_app.data.repositoryImplementation.ProfileRepositoryImpl
import com.example.cofeebreak.feature_app.domain.repository.ProfileRepository
import com.example.cofeebreak.feature_app.domain.usecase.CreateProfileUseCase

//val moduleProfile = module {
//    single<ProfileRepository> {
//        ProfileRepositoryImpl()
//    }
//    factory<CreateProfileUseCase> {
//        CreateProfileUseCase(get())
//    }
//}