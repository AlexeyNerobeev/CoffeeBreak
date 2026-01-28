package com.example.cofeebreak.feature_app.presentation.MyOrder

import androidx.compose.material3.SwipeToDismissBoxState
import com.example.cofeebreak.feature_app.domain.model.Order

data class MyOrderState(
    val orderList: List<Order> = listOf(),
    val error: Boolean = false,
    val totalAmount: Int = 0,
    val pay: Boolean = false,
    val userName: String = "",
    val address: String = "",
    val selectSbp: Boolean = true,
    val selectBankCard: Boolean = false
)
