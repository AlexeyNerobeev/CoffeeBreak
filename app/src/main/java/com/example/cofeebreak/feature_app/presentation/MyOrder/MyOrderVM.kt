package com.example.cofeebreak.feature_app.presentation.MyOrder

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cofeebreak.feature_app.domain.usecase.GetMyOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyOrderVM @Inject constructor(
    private val getMyOrderUseCase: GetMyOrderUseCase
): ViewModel() {
    private val _state = mutableStateOf(MyOrderState())
    val state: State<MyOrderState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val orderList = getMyOrderUseCase.invoke()
                var totalAmount = 0
                for(item in orderList){
                    totalAmount += item.price * item.count
                }
                _state.value = state.value.copy(
                    orderList = orderList,
                    totalAmount = totalAmount
                )
            } catch (ex: Exception){
                Log.e("supabase", ex.message.toString())
                _state.value = state.value.copy(
                    error = true
                )
            }
        }
    }

    fun onEvent(event: MyOrderEvent){
        when(event){
            MyOrderEvent.ChangeError -> {
                _state.value = state.value.copy(
                    error = false
                )
            }
        }
    }
}