package com.inventrack.data.remote.dto

data class WarehouseDto(
    val id: Int,
    val name: String,
    val address: String?
)

data class CreateWarehouseDto(
    val name: String,
    val address: String?
)
