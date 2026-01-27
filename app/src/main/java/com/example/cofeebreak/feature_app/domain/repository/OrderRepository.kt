package com.example.cofeebreak.feature_app.domain.repository

import com.example.cofeebreak.feature_app.domain.model.Order

interface OrderRepository {
    suspend fun getMyOrder(): List<Order>
}