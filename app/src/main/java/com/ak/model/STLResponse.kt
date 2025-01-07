package com.ak.model

data class STLResponse(
    val soilTestingLaboratoriesList: List<SoilTestingLaboratories>,
    val success: Boolean,
    val successCode: String
)