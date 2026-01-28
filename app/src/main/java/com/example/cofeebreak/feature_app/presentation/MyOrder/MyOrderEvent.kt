package com.example.cofeebreak.feature_app.presentation.MyOrder

sealed class MyOrderEvent {
    data object ChangeError: MyOrderEvent()
    data class DeleteItem(val value: Int): MyOrderEvent()
    data object Pay: MyOrderEvent()
    data object SelectSbp: MyOrderEvent()
    data object SelectBankCard: MyOrderEvent()
}