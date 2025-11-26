package com.inventrack.domain.model

data class StockMovement(
    val id: Int,
    val productId: Int,
    val productName: String?,
    val quantity: Int,
    val type: String,
    val warehouseId: Int?,
    val createdAt: String?
)
