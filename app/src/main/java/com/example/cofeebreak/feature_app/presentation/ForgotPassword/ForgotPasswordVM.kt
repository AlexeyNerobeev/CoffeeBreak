package com.example.cofeebreak.feature_app.presentation.ForgotPassword

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cofeebreak.feature_app.domain.usecase.IsEmailValidUseCase

class ForgotPasswordVM(
    private val isEmailValidUseCase: IsEmailValidUseCase
): ViewModel() {
    private val _state = mutableStateOf(ForgotPasswordState())
    val state: State<ForgotPasswordState> = _state

    fun onEvent(event: ForgotPasswordEvent){
        when(event){
            is ForgotPasswordEvent.EnteredEmail -> {
               _state.value = state.value.copy(
                   email = event.value
               )
            }
            ForgotPasswordEvent.ValidEmail -> {
                _state.value = state.value.copy(
                    validEmail = isEmailValidUseCase.invoke(state.value.email)
                )
            }
            ForgotPasswordEvent.ChangeError -> {
                _state.value = state.value.copy(
                    error = !state.value.error
                )
            }
            ForgotPasswordEvent.ErrorValidEmail -> {
                _state.value = state.value.copy(
                    errorValidEmail = !state.value.errorValidEmail
                )
            }
        }
    }
}