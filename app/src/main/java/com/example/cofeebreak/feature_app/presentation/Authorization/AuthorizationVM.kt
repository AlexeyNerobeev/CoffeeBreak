package com.example.cofeebreak.feature_app.presentation.Authorization

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cofeebreak.feature_app.domain.usecase.GetCurrentUserIdUseCase
import com.example.cofeebreak.feature_app.domain.usecase.IsEmailValidUseCase
import com.example.cofeebreak.feature_app.domain.usecase.IsPasswordStrongUseCase
import com.example.cofeebreak.feature_app.domain.usecase.SaveCurrentUserIdUseCase
import com.example.cofeebreak.feature_app.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationVM @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    private val saveCurrentUserIdUseCase: SaveCurrentUserIdUseCase,
    private val isEmailValidUseCase: IsEmailValidUseCase,
    private val isPasswordStrongUseCase: IsPasswordStrongUseCase
) : ViewModel() {
    private val _state = mutableStateOf(AuthorizationState())
    val state: State<AuthorizationState> = _state

//    private val _email = MutableStateFlow(state.value.email)
//    val email = _email
//        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), AuthorizationState())
//
//    private val _password = MutableStateFlow(state.value.password)
//
//    val isUserCanAuthenticate = combine(_email, _password) {
//        Patterns.EMAIL_ADDRESS.matcher(_email.value).matches() &&
//                _password.value.length > 8
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), false)


    fun onEvent(event: AuthorizationEvent) {
        when (event) {
            is AuthorizationEvent.EnteredEmail -> {
                _state.value = state.value.copy(
                    email = event.value
                )
            }

            is AuthorizationEvent.EnteredPassword -> {
                _state.value = state.value.copy(
                    password = event.value
                )
            }

            is AuthorizationEvent.SignIn -> {
                if (isEmailValidUseCase(state.value.email) &&
                    isPasswordStrongUseCase(state.value.password)
                ) {
                    viewModelScope.launch(Dispatchers.IO) {
                        try {
                            signInUseCase.invoke(
                                email = state.value.email,
                                password = state.value.password
                            )
                            _state.value = state.value.copy(
                                isComplete = true
                            )
                        } catch (ex: Exception) {
                            _state.value = state.value.copy(
                                error = true,
                                progressIndicator = false
                            )
                            Log.e("supabase", ex.message.toString())
                        }
                    }
                } else{
                    _state.value = state.value.copy(
                        error = true,
                        progressIndicator = false
                    )
                }
            }

            is AuthorizationEvent.PasswordVisible -> {
                _state.value = state.value.copy(
                    passwordVisible = !state.value.passwordVisible
                )
            }

            AuthorizationEvent.ClearError -> {
                _state.value = state.value.copy(
                    error = false
                )
            }

            AuthorizationEvent.ProgressIndicator -> {
                _state.value = state.value.copy(
                    progressIndicator = true
                )
            }
            AuthorizationEvent.SaveCurrentUserId -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val id = getCurrentUserIdUseCase.invoke()
                        saveCurrentUserIdUseCase.invoke(id)
                    } catch (ex: Exception){
                        Log.e("sharedPrefs", ex.message.toString())
                    }
                }
            }
            AuthorizationEvent.EmailValid -> {
                _state.value = state.value.copy(
                    validEmail = isEmailValidUseCase.invoke(state.value.email)
                )
            }
        }
    }
}

//@Composable
//fun Ty(viewModel: AuthorizationVM) {
//    viewModel.email.collectAsStateWithLifecycle()
//    val canAuthorize by viewModel.isUserCanAuthenticate.collectAsStateWithLifecycle()
//
//    Button(
//        onClick = {},
//        enabled = canAuthorize
//    ) { }
//}