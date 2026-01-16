package com.example.cofeebreak.feature_app.presentation.TwoFactorVerification

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class TwoFactorVerificationVM: ViewModel() {
    private val _state = mutableStateOf(TwoFactorVerificationState())
    val state: State<TwoFactorVerificationState> = _state
    fun onEvent(event: TwoFactorVerificationEvent){
        when(event){
            is TwoFactorVerificationEvent.EnteredFirstNumber -> {
                _state.value = state.value.copy(
                    firstNumber = event.value
                )
            }
            is TwoFactorVerificationEvent.EnteredSecondNumber -> {
                _state.value = state.value.copy(
                    secondNumber = event.value
                )
            }
            is TwoFactorVerificationEvent.EnteredThirdNumber -> {
                _state.value = state.value.copy(
                    thirdNumber = event.value
                )
            }
            is TwoFactorVerificationEvent.EnteredFourthNumber -> {
                _state.value = state.value.copy(
                    fourthNumber = event.value
                )
            }
            TwoFactorVerificationEvent.ChangeEmptyFieldsError -> {
                _state.value = state.value.copy(
                    emptyFieldsError = !state.value.emptyFieldsError
                )
            }
        }
    }
}