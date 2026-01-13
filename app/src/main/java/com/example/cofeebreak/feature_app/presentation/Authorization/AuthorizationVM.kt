package com.example.cofeebreak.feature_app.presentation.Authorization

import android.util.Log
import android.util.Patterns
import androidx.compose.animation.core.AnimationState
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.cofeebreak.feature_app.domain.usecase.SignInUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthorizationVM(
    private val signInUseCase: SignInUseCase
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
        when(event){
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
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        signInUseCase.invoke(email = state.value.email,
                            password = state.value.password)
                        _state.value = state.value.copy(
                            isComplete = true
                        )
                    } catch (ex: Exception){
                        Log.e("supabase", ex.message.toString())
                    }
                }
            }
            is AuthorizationEvent.PasswordVisible -> {
                _state.value = state.value.copy(
                    passwordVisible = !state.value.passwordVisible
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