package com.example.cofeebreak.feature_app.presentation.CoffeeCountry

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cofeebreak.feature_app.domain.usecase.GetCoffeeCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeCountryVM @Inject constructor(
    private val getCoffeeCountryUseCase: GetCoffeeCountryUseCase
): ViewModel() {
    private val _state = mutableStateOf(CoffeeCountryState())
    val state: State<CoffeeCountryState> = _state

    init{
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val coffeeCountryList = getCoffeeCountryUseCase.invoke()
                _state.value = state.value.copy(
                    coffeeCountryList = coffeeCountryList
                )
            } catch (ex: Exception){
                Log.e("supabase", ex.message.toString())
                _state.value = state.value.copy(
                    error = true
                )
            }
        }
    }

    fun onEvent(event: CoffeeCountryEvent){
        when(event){
            CoffeeCountryEvent.ChangeError -> {
                _state.value = state.value.copy(
                    error = false
                )
            }
        }
    }
}