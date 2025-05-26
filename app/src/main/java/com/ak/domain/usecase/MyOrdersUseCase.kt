package com.ak.domain.usecase

import com.ak.data.repository.AKRepository
import com.google.gson.JsonObject
import javax.inject.Inject

class MyOrdersUseCase @Inject constructor(
    private val soilTestRepository: AKRepository
) {
    suspend operator fun invoke(ordersRequest: JsonObject) =
        soilTestRepository.getMyOrders(ordersRequest)
}