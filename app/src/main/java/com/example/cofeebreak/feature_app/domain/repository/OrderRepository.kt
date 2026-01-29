package com.example.cofeebreak.feature_app.domain.repository

import com.example.cofeebreak.feature_app.domain.model.Order
import com.example.cofeebreak.feature_app.domain.model.Profile

interface OrderRepository {
    suspend fun getMyOrder(id: Profile): List<Order>
    suspend fun getCurrentOrder(id: Profile): List<Order>
    suspend fun getHistoryOrder(id: Profile): List<Order>
}