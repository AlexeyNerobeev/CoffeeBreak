package com.example.cofeebreak.feature_app.presentation.CoffeeType

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cofeebreak.feature_app.domain.usecase.GetCoffeeTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeTypeVM @Inject constructor(
    private val getCoffeeTypeUseCase: GetCoffeeTypeUseCase
): ViewModel() {
    private val _state = mutableStateOf(CoffeeTypeState())
    val state: State<CoffeeTypeState> = _state

    init{
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val coffeeTypeList = getCoffeeTypeUseCase.invoke()
                _state.value = state.value.copy(
                    coffeeTypeList = coffeeTypeList
                )
            } catch (ex: Exception){
                Log.e("supabase", ex.message.toString())
                _state.value = state.value.copy(
                    error = true
                )
            }
        }
    }

    fun onEvent(event: CoffeeTypeEvent){
        when(event){
            CoffeeTypeEvent.ChangeError -> {
                _state.value = state.value.copy(
                    error = false
                )
            }
        }
    }
}