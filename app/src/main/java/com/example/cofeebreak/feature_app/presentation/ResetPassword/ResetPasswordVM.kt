package com.example.cofeebreak.feature_app.presentation.ResetPassword

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cofeebreak.feature_app.domain.usecase.IsPasswordStrongUseCase

class ResetPasswordVM(
    private val isPasswordStrongUseCase: IsPasswordStrongUseCase
): ViewModel() {
    private val _state = mutableStateOf(ResetPasswordState())
    val state: State<ResetPasswordState> = _state

    fun onEvent(event: ResetPasswordEvent){
        when(event){
            ResetPasswordEvent.ChangeEmptyFieldError -> {
                _state.value = state.value.copy(
                    emptyFieldError = !state.value.emptyFieldError
                )
            }
            is ResetPasswordEvent.EnteredPassword -> {
                _state.value = state.value.copy(
                    password = event.value
                )
            }
            ResetPasswordEvent.IsPasswordStrong -> {
                _state.value = state.value.copy(
                    isPasswordStrong = isPasswordStrongUseCase.invoke(state.value.password)
                )
            }
            ResetPasswordEvent.PasswordVisible -> {
                _state.value = state.value.copy(
                    passwordVisible = !state.value.passwordVisible
                )
            }
            ResetPasswordEvent.ChangePasswordError ->{
                _state.value = state.value.copy(
                    passwordError = !state.value.passwordError
                )
            }
        }
    }
}