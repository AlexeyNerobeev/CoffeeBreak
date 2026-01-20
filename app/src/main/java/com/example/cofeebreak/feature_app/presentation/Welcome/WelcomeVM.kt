package com.example.cofeebreak.feature_app.presentation.Welcome

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cofeebreak.feature_app.domain.usecase.LoadCurrentUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeVM @Inject constructor(
    private val loadCurrentUserIdUseCase: LoadCurrentUserIdUseCase
): ViewModel() {
    private val _state = mutableStateOf(WelcomeState())
    val state: State<WelcomeState> = _state

    private val _channel = Channel<WelcomeAction>()
    val channel = _channel.receiveAsFlow()

    init {
        viewModelScope.launch {
            loadCurrentUserIdUseCase.invoke().id?.let {
                _channel.send(WelcomeAction.OnSuccessLoadedSession)
            }
            _channel.send(WelcomeAction.UnsuccessLoadedSession)
        }
    }

    fun onEvent(event: WelcomeEvent){
        when(event){
            WelcomeEvent.LoadCurrentUserId -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val id = loadCurrentUserIdUseCase.invoke().id
                        _state.value = state.value.copy(
                            id = id
                        )
                    } catch(ex: Exception) {
                        Log.e("sharedPrefs", ex.message.toString())
                    }
                }
            }
        }
    }
}