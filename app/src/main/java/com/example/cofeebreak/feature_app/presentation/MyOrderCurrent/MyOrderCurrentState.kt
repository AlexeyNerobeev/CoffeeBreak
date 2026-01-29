package com.example.cofeebreak.feature_app.presentation.MyOrderCurrent

import com.example.cofeebreak.feature_app.domain.model.Order

data class MyOrderCurrentState(
    val select: Int = 1,
    val error: Boolean = false,
    val currentOrderList: List<Order> = listOf(),
    val historyOrderList: List<Order> = listOf()
)