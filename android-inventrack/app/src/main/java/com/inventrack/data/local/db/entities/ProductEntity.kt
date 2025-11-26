package com.inventrack.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String?,
    val sku: String?,
    val barcode: String?,
    val category: String?,
    val quantity: Int,
    val warehouseId: Int?,
    val imageUrl: String?
)
