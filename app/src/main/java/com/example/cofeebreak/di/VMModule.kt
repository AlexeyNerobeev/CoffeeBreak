// di/ViewModelModule.kt
package com.example.cofeebreak.di

import com.example.cofeebreak.feature_app.domain.usecase.CreateProfileUseCase
import com.example.cofeebreak.feature_app.domain.usecase.GetCoffeeListUseCase
import com.example.cofeebreak.feature_app.domain.usecase.GetCurrentUserIdUseCase
import com.example.cofeebreak.feature_app.domain.usecase.GetUserNameUseCase
import com.example.cofeebreak.feature_app.domain.usecase.IsEmailValidUseCase
import com.example.cofeebreak.feature_app.domain.usecase.IsPasswordStrongUseCase
import com.example.cofeebreak.feature_app.domain.usecase.LoadCurrentUserIdUseCase
import com.example.cofeebreak.feature_app.domain.usecase.SaveCurrentUserIdUseCase
import com.example.cofeebreak.feature_app.domain.usecase.SignInUseCase
import com.example.cofeebreak.feature_app.domain.usecase.SignUpUseCase
import com.example.cofeebreak.feature_app.presentation.Authorization.AuthorizationVM
import com.example.cofeebreak.feature_app.presentation.ForgotPassword.ForgotPasswordVM
import com.example.cofeebreak.feature_app.presentation.Menu.MenuScreenVM
import com.example.cofeebreak.feature_app.presentation.ResetPassword.ResetPasswordVM
import com.example.cofeebreak.feature_app.presentation.SignUp.SignUpVM
import com.example.cofeebreak.feature_app.presentation.TwoFactorVerification.TwoFactorVerificationVM
import com.example.cofeebreak.feature_app.presentation.Welcome.WelcomeVM
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideSignUpVM(
        signUpUseCase: SignUpUseCase,
        createProfileUseCase: CreateProfileUseCase,
        isPasswordStrongUseCase: IsPasswordStrongUseCase,
        isEmailValidUseCase: IsEmailValidUseCase
    ): SignUpVM {
        return SignUpVM(
            signUpUseCase = signUpUseCase,
            createProfileUseCase = createProfileUseCase,
            isPasswordStrongUseCase = isPasswordStrongUseCase,
            isEmailValidUseCase = isEmailValidUseCase
        )
    }

    @Provides
    @ViewModelScoped
    fun provideAuthorizationVM(
        signInUseCase: SignInUseCase,
        getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
        saveCurrentUserIdUseCase: SaveCurrentUserIdUseCase,
        isEmailValidUseCase: IsEmailValidUseCase,
        isPasswordStrongUseCase: IsPasswordStrongUseCase
    ): AuthorizationVM {
        return AuthorizationVM(
            signInUseCase = signInUseCase,
            getCurrentUserIdUseCase = getCurrentUserIdUseCase,
            saveCurrentUserIdUseCase = saveCurrentUserIdUseCase,
            isEmailValidUseCase = isEmailValidUseCase,
            isPasswordStrongUseCase = isPasswordStrongUseCase
        )
    }

    @Provides
    @ViewModelScoped
    fun provideWelcomeVM(
        loadCurrentUserIdUseCase: LoadCurrentUserIdUseCase
    ): WelcomeVM {
        return WelcomeVM(
            loadCurrentUserIdUseCase = loadCurrentUserIdUseCase
        )
    }

    @Provides
    @ViewModelScoped
    fun provideForgotPasswordVM(
        isEmailValidUseCase: IsEmailValidUseCase
    ): ForgotPasswordVM {
        return ForgotPasswordVM(
            isEmailValidUseCase = isEmailValidUseCase
        )
    }

    @Provides
    @ViewModelScoped
    fun provideTwoFactorVerificationVM(): TwoFactorVerificationVM {
        return TwoFactorVerificationVM()
    }

    @Provides
    @ViewModelScoped
    fun provideResetPasswordVM(
        isPasswordStrongUseCase: IsPasswordStrongUseCase
    ): ResetPasswordVM {
        return ResetPasswordVM(
            isPasswordStrongUseCase = isPasswordStrongUseCase
        )
    }
}