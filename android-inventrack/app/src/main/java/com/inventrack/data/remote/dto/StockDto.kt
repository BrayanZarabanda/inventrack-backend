package com.inventrack.data.remote.dto

data class CreateStockDto(
    val productId: Int,
    val quantity: Int,
    val warehouseId: Int?,
    val note: String? = null
)

data class StockMovementDto(
    val id: Int,
    val productId: Int,
    val productName: String?,
    val quantity: Int,
    val type: String,
    val warehouseId: Int?,
    val createdAt: String?
)
