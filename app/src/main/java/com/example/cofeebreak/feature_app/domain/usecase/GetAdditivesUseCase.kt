package com.example.cofeebreak.feature_app.domain.usecase

import com.example.cofeebreak.feature_app.domain.model.Additives
import com.example.cofeebreak.feature_app.domain.repository.AdditivesRepository

class GetAdditivesUseCase(private val additivesRepository: AdditivesRepository) {
    suspend operator fun invoke(): List<Additives>{
        return additivesRepository.getAdditives()
    }
}