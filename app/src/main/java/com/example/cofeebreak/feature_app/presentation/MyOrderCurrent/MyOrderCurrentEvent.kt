package com.example.cofeebreak.feature_app.presentation.MyOrderCurrent

import com.example.cofeebreak.feature_app.presentation.MyOrder.MyOrderEvent

sealed class MyOrderCurrentEvent {
    data class OnTabSelected(val value: Int): MyOrderCurrentEvent()
    data object ChangeError: MyOrderCurrentEvent()
}