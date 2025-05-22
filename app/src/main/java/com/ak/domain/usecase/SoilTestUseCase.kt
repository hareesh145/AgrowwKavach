package com.ak.domain.usecase

import com.ak.data.repository.AKRepository
import com.google.gson.JsonObject
import javax.inject.Inject

class SoilTestUseCase @Inject constructor(
    private val soilTestRepository: AKRepository
) {
    suspend operator fun invoke(soilTestRequest: JsonObject) =
        soilTestRepository.getSoilTestings(soilTestRequest)
}