package com.example.cofeebreak.di

import com.example.cofeebreak.feature_app.presentation.Authorization.AuthorizationVM
import com.example.cofeebreak.feature_app.presentation.SignUp.SignUpVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val moduleVM = module {
    viewModel<AuthorizationVM>{
        AuthorizationVM(get())
    }
    viewModel<SignUpVM> {
        SignUpVM(get(), get())
    }
}