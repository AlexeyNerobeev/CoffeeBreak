package com.example.cofeebreak.feature_app.domain.usecase

import com.example.cofeebreak.feature_app.domain.model.Order
import com.example.cofeebreak.feature_app.domain.model.Profile
import com.example.cofeebreak.feature_app.domain.repository.OrderRepository

class GetHistoryOrderUseCase(private val orderRepository: OrderRepository) {
    suspend operator fun invoke(id: Profile): List<Order>{
        return orderRepository.getHistoryOrder(id)
    }
}