package com.example.cofeebreak.feature_app.presentation.SignUp

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cofeebreak.feature_app.domain.usecase.CreateProfileUseCase
import com.example.cofeebreak.feature_app.domain.usecase.IsEmailValidUseCase
import com.example.cofeebreak.feature_app.domain.usecase.IsPasswordStrongUseCase
import com.example.cofeebreak.feature_app.domain.usecase.SignUpUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpVM(
    private val signUpUseCase: SignUpUseCase,
    private val createProfileUseCase: CreateProfileUseCase,
    private val isPasswordStrongUseCase: IsPasswordStrongUseCase,
    private val isEmailValidUseCase: IsEmailValidUseCase
) : ViewModel() {
    private val _state = mutableStateOf(SignUpState())
    val state: State<SignUpState> = _state

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.EnteredEmailAddress -> {
                _state.value = state.value.copy(
                    emailAddress = event.value
                )
            }

            is SignUpEvent.EnteredName -> {
                _state.value = state.value.copy(
                    name = event.value
                )
            }

            is SignUpEvent.EnteredPassword -> {
                _state.value = state.value.copy(
                    password = event.value
                )
            }

            is SignUpEvent.EnteredPhone -> {
                _state.value = state.value.copy(
                    phone = event.value
                )
            }
            is SignUpEvent.SignUp -> {
                if (state.value.name.isEmpty() ||
                    state.value.emailAddress.isEmpty() ||
                    state.value.phone.isEmpty() ||
                    state.value.password.isEmpty()){
                    _state.value = state.value.copy(
                        fieldsEmpty = true
                    )
                } else {
                    viewModelScope.launch(Dispatchers.IO) {
                        try {
                            val canSignUp = isEmailValidUseCase(state.value.emailAddress)
                            if (canSignUp) {
                                if (isPasswordStrongUseCase(state.value.password)) {
                                    signUpUseCase.invoke(
                                        state.value.emailAddress,
                                        state.value.password
                                    )
                                    createProfileUseCase.invoke(
                                        name = state.value.name,
                                        phone = state.value.phone
                                    )
                                    _state.value = state.value.copy(
                                        isComplete = true
                                    )
                                } else{
                                    _state.value = state.value.copy(
                                        passwordError = true,
                                        progressIndicator = false
                                    )
                                }
                            } else {
                                _state.value = state.value.copy(
                                    error = true,
                                    progressIndicator = false
                                )
                            }
                        } catch (ex: Exception) {
                            _state.value = state.value.copy(
                                error = true,
                                progressIndicator = false
                            )
                            Log.e("supabase", ex.message.toString())
                        }
                    }
                }
            }
            SignUpEvent.PasswordVisible -> {
                _state.value = state.value.copy(
                    passwordVisible = !state.value.passwordVisible
                )
            }
            SignUpEvent.ClearErrors -> {
                _state.value = state.value.copy(
                    error = false,
                    fieldsEmpty = false,
                    passwordError = false
                )
            }
            SignUpEvent.ProgressIndicator -> {
                _state.value = state.value.copy(
                    progressIndicator = true
                )
            }
            SignUpEvent.IsValidEmail -> {
                _state.value = state.value.copy(
                     validEmail = isEmailValidUseCase.invoke(state.value.emailAddress)
                )
            }
        }
    }
}