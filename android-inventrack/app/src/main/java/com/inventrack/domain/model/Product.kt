package com.inventrack.domain.model

data class Product(
    val id: Int,
    val name: String,
    val description: String?,
    val sku: String?,
    val barcode: String?,
    val category: String?,
    val quantity: Int,
    val warehouseId: Int?,
    val imageUrl: String?
)
