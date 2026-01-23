package com.example.cofeebreak.feature_app.presentation.OrderOptions

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderOptionsVM @Inject constructor(): ViewModel() {
    private val _state = mutableStateOf(OrderOptionsState())
    val state: State<OrderOptionsState> = _state

    fun onEvent(event: OrderOptionsEvent){
        when(event){
            OrderOptionsEvent.MinusCoffeeCount -> {
                if(state.value.coffeeCount > 0)
                _state.value = state.value.copy(
                    coffeeCount = state.value.coffeeCount - 1
                )
            }
            OrderOptionsEvent.PlusCoffeeCount -> {
                _state.value = state.value.copy(
                    coffeeCount = state.value.coffeeCount + 1
                )
            }
            OrderOptionsEvent.Switch -> {
                _state.value = state.value.copy(
                    switch = !state.value.switch
                )
            }
        }
    }
}