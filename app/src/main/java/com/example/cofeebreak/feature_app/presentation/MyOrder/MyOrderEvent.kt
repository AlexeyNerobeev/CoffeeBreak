package com.example.cofeebreak.feature_app.presentation.MyOrder

sealed class MyOrderEvent {
    data object ChangeError: MyOrderEvent()
}