package com.example.cofeebreak.feature_app.presentation.ForgotPassword

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cofeebreak.feature_app.domain.model.User
import com.example.cofeebreak.feature_app.domain.usecase.IsEmailValidUseCase
import com.example.cofeebreak.feature_app.domain.usecase.ResetPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordVM @Inject constructor(
    private val isEmailValidUseCase: IsEmailValidUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase
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
            ForgotPasswordEvent.ResetPassword -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        resetPasswordUseCase.invoke(User(email = state.value.email))
                        _state.value = state.value.copy(
                            isComplete = true
                        )
                    } catch (ex: Exception){
                        Log.e("reset password", ex.message.toString())
                    }
                }
            }
        }
    }
}