package com.example.cofeebreak.di

import com.example.cofeebreak.feature_app.presentation.Authorization.AuthorizationVM
import com.example.cofeebreak.feature_app.presentation.SignUp.SignUpVM
import com.example.cofeebreak.feature_app.presentation.Welcome.WelcomeVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val moduleVM = module {
    viewModel<AuthorizationVM>{
        AuthorizationVM(get(),
            get(),
            get(),
            get(),
            get())
    }
    viewModel<SignUpVM> {
        SignUpVM(get(),
            get(),
            get(),
            get())
    }
    viewModel<WelcomeVM> {
        WelcomeVM(get())
    }
}