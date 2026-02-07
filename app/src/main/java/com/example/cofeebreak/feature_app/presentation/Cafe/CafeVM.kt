package com.example.cofeebreak.feature_app.presentation.Cafe

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cofeebreak.feature_app.domain.model.Profile
import com.example.cofeebreak.feature_app.domain.usecase.GetCoffeeShopsListUseCase
import com.example.cofeebreak.feature_app.domain.usecase.SaveCoffeeShopAddressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CafeVM @Inject constructor(
    private val getCoffeeShopsListUseCase: GetCoffeeShopsListUseCase,
    private val saveCoffeeShopAddressUseCase: SaveCoffeeShopAddressUseCase
): ViewModel() {
    private val _state = mutableStateOf(CafeState())
    val state: State<CafeState> = _state

    init{
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val coffeeShopList = getCoffeeShopsListUseCase.invoke()
                _state.value = state.value.copy(
                    coffeeShopList = coffeeShopList,
                    load = false
                )
            } catch (ex: Exception){
                Log.e("supabase", ex.message.toString())
            }
        }
    }

    fun onEvent(event: CafeEvent){
        when(event){
            is CafeEvent.SaveCoffeeShopAddress -> {
                viewModelScope.launch {
                    try {
                        saveCoffeeShopAddressUseCase.invoke(Profile(coffee_shop_address = event.value))
                    } catch (ex: Exception){
                        Log.e("supabase", ex.message.toString())
                    }
                }
            }
        }
    }
}