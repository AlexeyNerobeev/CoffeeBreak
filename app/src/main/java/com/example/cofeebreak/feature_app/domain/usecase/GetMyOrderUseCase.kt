package com.example.cofeebreak.feature_app.domain.usecase

import com.example.cofeebreak.feature_app.domain.model.Order
import com.example.cofeebreak.feature_app.domain.repository.OrderRepository

class GetMyOrderUseCase(private val orderRepository: OrderRepository) {
    suspend operator fun invoke(): List<Order>{
        return orderRepository.getMyOrder()
    }
}