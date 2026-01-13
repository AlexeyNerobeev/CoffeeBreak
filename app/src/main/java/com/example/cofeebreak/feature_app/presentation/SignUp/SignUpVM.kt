package com.example.cofeebreak.feature_app.presentation.SignUp

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.cofeebreak.feature_app.domain.usecase.CreateProfileUseCase
import com.example.cofeebreak.feature_app.domain.usecase.SignUpUseCase
import com.example.cofeebreak.feature_app.presentation.Authorization.AuthorizationState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SignUpVM(
    private val signUpUseCase: SignUpUseCase,
    private val createProfileUseCase: CreateProfileUseCase
) : ViewModel() {
    private val _state = mutableStateOf(SignUpState())
    val state: State<SignUpState> = _state

    private val _email = MutableStateFlow(state.value.emailAddress)
    private val _password = MutableStateFlow(state.value.password)

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
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val canSignUp = Patterns.EMAIL_ADDRESS.matcher(state.value.emailAddress).matches() &&
                                state.value.password.length > 8
                        if (canSignUp){
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
                        }
                        else{
                            _state.value = state.value.copy(
                                emailAddress = "некорректные почта или пароль"
                            )
                        }
                    } catch (ex: Exception) {
                        Log.e("supabase", ex.message.toString())
                    }
                }
            }
            SignUpEvent.PasswordVisible -> {
                _state.value = state.value.copy(
                    passwordVisible = !state.value.passwordVisible
                )
            }
        }
    }
}