package com.example.cofeebreak.feature_app.presentation.MyOrderCurrent

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cofeebreak.feature_app.domain.model.Profile
import com.example.cofeebreak.feature_app.domain.usecase.GetCoffeeShopAddressUseCase
import com.example.cofeebreak.feature_app.domain.usecase.GetCurrentOrderUseCase
import com.example.cofeebreak.feature_app.domain.usecase.GetHistoryOrderUseCase
import com.example.cofeebreak.feature_app.domain.usecase.LoadCurrentUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import java.time.OffsetDateTime
import java.time.OffsetTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MyOrderCurrentVM @Inject constructor(
    private val getCurrentOrderUseCase: GetCurrentOrderUseCase,
    private val getHistoryOrderUseCase: GetHistoryOrderUseCase,
    private val loadCurrentUserIdUseCase: LoadCurrentUserIdUseCase,
    private val getCoffeeShopAddressUseCase: GetCoffeeShopAddressUseCase
): ViewModel() {
    private val _state = mutableStateOf(MyOrderCurrentState())
    val state: State<MyOrderCurrentState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userId = loadCurrentUserIdUseCase.invoke().id.toString()
                val currentOrder = getCurrentOrderUseCase.invoke(Profile(user_id = userId))
                val historyOrder = getHistoryOrderUseCase.invoke(Profile(user_id = userId))
                val coffeeShopAddress = getCoffeeShopAddressUseCase.invoke(Profile(user_id = userId)).coffee_shop_address
                _state.value = state.value.copy(
                    currentOrderList = currentOrder,
                    historyOrderList = historyOrder,
                    address = coffeeShopAddress.toString(),
                    load = false
                )
            } catch (ex: Exception){
                Log.e("supabase", ex.message.toString())
                _state.value = state.value.copy(
                    error = true
                )
            }
        }
    }

    fun formatOrderDate(
        createdAt: String,
        timeTo: String
    ): String {
        val localeRu = Locale("ru")

        val createdDateTime = OffsetDateTime.parse(
            createdAt.replace(" ", "T")
        ).atZoneSameInstant(ZoneId.systemDefault())

        val dateFormatter =
            DateTimeFormatter.ofPattern("d MMMM", localeRu)
        val timeFormatter =
            DateTimeFormatter.ofPattern("HH:mm")

        val datePart = createdDateTime.format(dateFormatter)
        val timePart = createdDateTime.format(timeFormatter)

        val normalizedTimeTo =
            if (timeTo.length == 11) "$timeTo:00" else timeTo

        val timeToParsed = OffsetTime.parse(normalizedTimeTo)
        val timeToPart = timeToParsed.format(timeFormatter)

        return "$datePart | $timePart | к $timeToPart"
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