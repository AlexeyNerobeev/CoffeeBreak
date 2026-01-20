package com.example.cofeebreak.di

import com.example.cofeebreak.feature_app.data.repositoryImplementation.AuthRepositoryImpl
import com.example.cofeebreak.feature_app.domain.repository.AuthRepository
import com.example.cofeebreak.feature_app.domain.usecase.GetCurrentUserIdUseCase
import com.example.cofeebreak.feature_app.domain.usecase.IsEmailValidUseCase
import com.example.cofeebreak.feature_app.domain.usecase.IsPasswordStrongUseCase
import com.example.cofeebreak.feature_app.domain.usecase.SignInUseCase
import com.example.cofeebreak.feature_app.domain.usecase.SignUpUseCase

//val moduleAuth = module {
//    single<AuthRepository> {
//        AuthRepositoryImpl()
//    }
//    factory<SignInUseCase> {
//        SignInUseCase(get())
//    }
//    factory<SignUpUseCase> {
//        SignUpUseCase(get())
//    }
//    factory<GetCurrentUserIdUseCase>{
//        GetCurrentUserIdUseCase(get())
//    }
//    factory<IsEmailValidUseCase> {
//        IsEmailValidUseCase()
//    }
//    factory<IsPasswordStrongUseCase> {
//        IsPasswordStrongUseCase()
//    }
//}