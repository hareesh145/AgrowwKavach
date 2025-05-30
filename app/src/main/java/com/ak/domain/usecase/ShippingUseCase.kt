package com.ak.domain.usecase

import com.ak.data.repository.AKRepository
import com.google.gson.JsonObject
import javax.inject.Inject

class ShippingUseCase @Inject constructor(
    private val shippingRepository: AKRepository
){
    suspend operator fun invoke(shippingRequest: JsonObject) =
        shippingRepository.getShippings(shippingRequest)
}