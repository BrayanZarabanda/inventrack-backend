package com.inventrack.data.remote.dto

import com.inventrack.data.local.db.entities.ProductEntity
import com.inventrack.domain.model.Product

fun ProductDto.toDomain(): Product = Product(
    id = id,
    name = name,
    description = description,
    sku = sku,
    barcode = barcode,
    category = category,
    quantity = quantity,
    warehouseId = warehouseId,
    imageUrl = imageUrl
)

fun ProductDto.toEntity(): ProductEntity = ProductEntity(
    id = id,
    name = name,
    description = description,
    sku = sku,
    barcode = barcode,
    category = category,
    quantity = quantity,
    warehouseId = warehouseId,
    imageUrl = imageUrl
)

fun ProductEntity.toDomain(): Product = Product(
    id = id,
    name = name,
    description = description,
    sku = sku,
    barcode = barcode,
    category = category,
    quantity = quantity,
    warehouseId = warehouseId,
    imageUrl = imageUrl
)
