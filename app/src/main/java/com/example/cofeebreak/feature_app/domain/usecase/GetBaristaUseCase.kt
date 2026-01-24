package com.example.cofeebreak.feature_app.domain.usecase

import com.example.cofeebreak.feature_app.domain.model.Barista
import com.example.cofeebreak.feature_app.domain.repository.BaristaRepository

class GetBaristaUseCase(private val baristaRepository: BaristaRepository) {
    suspend operator fun invoke(): List<Barista>{
        return baristaRepository.getBarista()
    }
}