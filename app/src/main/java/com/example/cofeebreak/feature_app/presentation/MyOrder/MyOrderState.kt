package com.example.cofeebreak.feature_app.presentation.MyOrder

import com.example.cofeebreak.feature_app.domain.model.Order

data class MyOrderState(
    val orderList: List<Order> = listOf(),
    val error: Boolean = false,
    val totalAmount: Int = 0
)
