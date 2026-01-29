package com.example.cofeebreak.feature_app.presentation.MyOrderCurrent

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cofeebreak.feature_app.domain.model.Profile
import com.example.cofeebreak.feature_app.domain.usecase.GetCurrentOrderUseCase
import com.example.cofeebreak.feature_app.domain.usecase.GetHistoryOrderUseCase
import com.example.cofeebreak.feature_app.domain.usecase.LoadCurrentUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyOrderCurrentVM @Inject constructor(
    private val getCurrentOrderUseCase: GetCurrentOrderUseCase,
    private val getHistoryOrderUseCase: GetHistoryOrderUseCase,
    private val loadCurrentUserIdUseCase: LoadCurrentUserIdUseCase
): ViewModel() {
    private val _state = mutableStateOf(MyOrderCurrentState())
    val state: State<MyOrderCurrentState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userId = loadCurrentUserIdUseCase.invoke().id.toString()
                val currentOrder = getCurrentOrderUseCase.invoke(Profile(user_id = userId))
                val historyOrder = getHistoryOrderUseCase.invoke(Profile(user_id = userId))
                _state.value = state.value.copy(
                    currentOrderList = currentOrder,
                    historyOrderList = historyOrder
                )
            } catch (ex: Exception){
                Log.e("supabase", ex.message.toString())
                _state.value = state.value.copy(
                    error = true
                )
            }
        }
    }

    fun onEvent(event: MyOrderCurrentEvent){
        when(event){
            MyOrderCurrentEvent.ChangeError -> {
                _state.value = state.value.copy(
                    error = false
                )
            }
            is MyOrderCurrentEvent.OnTabSelected -> {
                _state.value = state.value.copy(
                    select = event.value
                )
            }
        }
    }
}