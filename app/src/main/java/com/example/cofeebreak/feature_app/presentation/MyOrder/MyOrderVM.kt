package com.example.cofeebreak.feature_app.presentation.MyOrder

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cofeebreak.feature_app.domain.model.Profile
import com.example.cofeebreak.feature_app.domain.usecase.GetCoffeeShopAddressUseCase
import com.example.cofeebreak.feature_app.domain.usecase.GetMyOrderUseCase
import com.example.cofeebreak.feature_app.domain.usecase.GetUserNameUseCase
import com.example.cofeebreak.feature_app.domain.usecase.LoadCurrentUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyOrderVM @Inject constructor(
    private val getMyOrderUseCase: GetMyOrderUseCase,
    private val getCoffeeShopAddressUseCase: GetCoffeeShopAddressUseCase,
    private val loadCurrentUserIdUseCase: LoadCurrentUserIdUseCase,
    private val getUserNameUseCase: GetUserNameUseCase
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
                val userId = loadCurrentUserIdUseCase.invoke().id.toString()
                val name = getUserNameUseCase.invoke(userId).name
                val address = getCoffeeShopAddressUseCase.invoke(Profile(user_id = userId)).coffee_shop_address
                _state.value = state.value.copy(
                    orderList = orderList,
                    totalAmount = totalAmount,
                    userName = name,
                    address = address.toString()
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
            is MyOrderEvent.DeleteItem -> {
                _state.value = state.value.copy(
                    orderList = state.value.orderList.filterNot { it.id == event.value }
                )
            }
            MyOrderEvent.Pay -> {
                _state.value = state.value.copy(
                    pay = !state.value.pay
                )
            }
            MyOrderEvent.SelectBankCard -> {
                _state.value = state.value.copy(
                    selectBankCard = !state.value.selectBankCard
                )
                if(state.value.selectSbp)
                    _state.value = state.value.copy(
                        selectSbp = false
                    )
            }
            MyOrderEvent.SelectSbp -> {
                _state.value = state.value.copy(
                    selectSbp = !state.value.selectSbp
                )
                if(state.value.selectBankCard)
                    _state.value = state.value.copy(
                        selectBankCard = false
                    )
            }
        }
    }
}