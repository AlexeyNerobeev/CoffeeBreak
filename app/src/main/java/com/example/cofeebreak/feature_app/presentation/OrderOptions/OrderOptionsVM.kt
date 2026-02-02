package com.example.cofeebreak.feature_app.presentation.OrderOptions

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cofeebreak.feature_app.domain.model.Coffee
import com.example.cofeebreak.feature_app.domain.model.Order
import com.example.cofeebreak.feature_app.domain.usecase.GetCoffeeByIdUseCase
import com.example.cofeebreak.feature_app.domain.usecase.LoadCurrentUserIdUseCase
import com.example.cofeebreak.feature_app.domain.usecase.SaveOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderOptionsVM @Inject constructor(
    private val getCoffeeByIdUseCase: GetCoffeeByIdUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val saveOrderUseCase: SaveOrderUseCase,
    private val loadCurrentUserIdUseCase: LoadCurrentUserIdUseCase

): ViewModel() {
    private val coffeeId: Int = savedStateHandle["coffeeId"]
        ?: throw IllegalArgumentException("coffeeId is missing")
    private val _state = mutableStateOf(OrderOptionsState())
    val state: State<OrderOptionsState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val coffee = getCoffeeByIdUseCase.invoke(Coffee(coffeeId))
                _state.value = state.value.copy(
                    coffee = coffee,
                    price = coffee.price,
                    totalPrice = coffee.price,
                    load = false
                )
            } catch(ex: Exception){
                Log.e("supabase", ex.message.toString())
            }
        }
    }

    fun onEvent(event: OrderOptionsEvent){
        when(event){
            OrderOptionsEvent.MinusCoffeeCount -> {
                if(state.value.coffeeCount > 1) {
                    val newCount = state.value.coffeeCount - 1
                    recalculateTotalPrice(coffeeCount = newCount)
                }
            }
            OrderOptionsEvent.PlusCoffeeCount -> {
                val newCount = state.value.coffeeCount + 1
                recalculateTotalPrice(coffeeCount = newCount)
            }
            OrderOptionsEvent.Switch -> {
                _state.value = state.value.copy(
                    switch = !state.value.switch
                )
            }
            is OrderOptionsEvent.SelectRistretto -> {
                recalculateTotalPrice(ristretto = event.value)
            }
            is OrderOptionsEvent.SelectVolume -> {
                recalculateTotalPrice(volume = event.value)
            }
            OrderOptionsEvent.SaveOrder -> {
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val userId = loadCurrentUserIdUseCase.invoke().id
                        val order = Order(name = state.value.coffee.coffee_name,
                            price = state.value.totalPrice,
                            coffee_image = state.value.coffee.coffee_image,
                            count = state.value.coffeeCount,
                            user_id = userId.toString(),
                            status = "not paid",
                            ristretto = state.value.ristretto,
                            volume = state.value.volume)
                        val orderId = saveOrderUseCase.invoke(order).id
                        _state.value = state.value.copy(
                            orderId = orderId,
                            isComplete = true
                        )
                    } catch (ex: Exception){
                        Log.e("supabase", ex.message.toString())
                        _state.value = state.value.copy(
                            progressIndicator = false
                        )
                    }
                }
            }
            OrderOptionsEvent.ProgressIndicator -> {
                _state.value = state.value.copy(
                    progressIndicator = true
                )
            }
        }
    }

    private fun recalculateTotalPrice(
        coffeeCount: Int = state.value.coffeeCount,
        ristretto: Int = state.value.ristretto,
        volume: Int = state.value.volume
    ) {
        val volumePrice = when(volume){
            250 -> 0
            350 -> 100
            450 -> 200
            else -> 0
        }
        val basePrice = state.value.price + ristretto + volumePrice
        val total = basePrice * coffeeCount

        _state.value = state.value.copy(
            coffeeCount = coffeeCount,
            ristretto = ristretto,
            volume = volume,
            totalPrice = total
        )
    }

}