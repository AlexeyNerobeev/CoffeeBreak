package com.example.cofeebreak.feature_app.domain.usecase

import com.example.cofeebreak.feature_app.domain.model.Redeem
import com.example.cofeebreak.feature_app.domain.repository.RedeemRepository

class GetRedeemUseCase(private val redeemRepository: RedeemRepository) {
    suspend operator fun invoke(): List<Redeem>{
        return redeemRepository.getRedeem()
    }
}