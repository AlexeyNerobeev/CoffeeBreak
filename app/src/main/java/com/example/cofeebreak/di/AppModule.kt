package com.example.cofeebreak.di

import android.content.Context
import android.content.SharedPreferences
import com.example.cofeebreak.feature_app.data.repositoryImplementation.AuthRepositoryImpl
import com.example.cofeebreak.feature_app.data.repositoryImplementation.CoffeeRepositoryImpl
import com.example.cofeebreak.feature_app.data.repositoryImplementation.CurrentSessionRepositoryImpl
import com.example.cofeebreak.feature_app.data.repositoryImplementation.ProfileRepositoryImpl
import com.example.cofeebreak.feature_app.data.supabase.Connect
import com.example.cofeebreak.feature_app.domain.repository.AuthRepository
import com.example.cofeebreak.feature_app.domain.repository.CoffeeRepository
import com.example.cofeebreak.feature_app.domain.repository.CurrentSessionRepository
import com.example.cofeebreak.feature_app.domain.repository.ProfileRepository
import com.example.cofeebreak.feature_app.domain.usecase.CreateProfileUseCase
import com.example.cofeebreak.feature_app.domain.usecase.DeleteCurrentUserIdUseCse
import com.example.cofeebreak.feature_app.domain.usecase.GetCoffeeListUseCase
import com.example.cofeebreak.feature_app.domain.usecase.GetCurrentUserIdUseCase
import com.example.cofeebreak.feature_app.domain.usecase.GetUserNameUseCase
import com.example.cofeebreak.feature_app.domain.usecase.IsEmailValidUseCase
import com.example.cofeebreak.feature_app.domain.usecase.IsPasswordStrongUseCase
import com.example.cofeebreak.feature_app.domain.usecase.LoadCurrentUserIdUseCase
import com.example.cofeebreak.feature_app.domain.usecase.SaveCurrentUserIdUseCase
import com.example.cofeebreak.feature_app.domain.usecase.SignInUseCase
import com.example.cofeebreak.feature_app.domain.usecase.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideProfileRepository(): ProfileRepository {
        return ProfileRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideCurrentSessionRepository(
        @ApplicationContext context: Context
    ): CurrentSessionRepository {
        return CurrentSessionRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideCoffeeRepository(): CoffeeRepository{
        return CoffeeRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideSignInUseCase(authRepository: AuthRepository): SignInUseCase {
        return SignInUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(authRepository: AuthRepository): SignUpUseCase {
        return SignUpUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideCreateProfileUseCase(profileRepository: ProfileRepository): CreateProfileUseCase {
        return CreateProfileUseCase(profileRepository)
    }

    @Provides
    @Singleton
    fun provideIsEmailValidUseCase(): IsEmailValidUseCase {
        return IsEmailValidUseCase()
    }

    @Provides
    @Singleton
    fun provideIsPasswordStrongUseCase(): IsPasswordStrongUseCase {
        return IsPasswordStrongUseCase()
    }

    @Provides
    @Singleton
    fun provideGetCurrentUserIdUseCase(
        authRepository: AuthRepository
    ): GetCurrentUserIdUseCase {
        return GetCurrentUserIdUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideSaveCurrentUserIdUseCase(
        currentSessionRepository: CurrentSessionRepository
    ): SaveCurrentUserIdUseCase {
        return SaveCurrentUserIdUseCase(currentSessionRepository)
    }

    @Provides
    @Singleton
    fun provideLoadCurrentUserIdUseCase(
        currentSessionRepository: CurrentSessionRepository
    ): LoadCurrentUserIdUseCase {
        return LoadCurrentUserIdUseCase(currentSessionRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteCurrentUserIdUseCse(
        currentSessionRepository: CurrentSessionRepository
    ): DeleteCurrentUserIdUseCse {
        return DeleteCurrentUserIdUseCse(currentSessionRepository)
    }

    @Provides
    @Singleton
    fun provideGetUserNameUseCase(
        profileRepository: ProfileRepository
    ): GetUserNameUseCase{
        return GetUserNameUseCase(profileRepository)
    }

    @Provides
    @Singleton
    fun provideGetCoffeeListUseCase(
        coffeeRepository: CoffeeRepository
    ): GetCoffeeListUseCase{
        return GetCoffeeListUseCase(coffeeRepository)
    }
}