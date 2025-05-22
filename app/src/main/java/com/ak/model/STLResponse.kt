package com.ak.model

data class STLResponse(
    val soilTestingLaboratoriesList: List<SoilTestingLaboratories>?=null,
    val success: Boolean?= null,
    val successCode: String?= null,
)