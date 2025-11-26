package com.inventrack.data.remote.dto

data class CreateProductDto(
    val name: String,
    val description: String?,
    val sku: String?,
    val barcode: String?,
    val category: String?,
    val quantity: Int,
    val warehouseId: Int?,
    val imageUrl: String?
) {
    companion object {
        fun fromDomain(p: com.inventrack.domain.model.Product) = CreateProductDto(
            name = p.name,
            description = p.description,
            sku = p.sku,
            barcode = p.barcode,
            category = p.category,
            quantity = p.quantity,
            warehouseId = p.warehouseId,
            imageUrl = p.imageUrl
        )
    }
}
