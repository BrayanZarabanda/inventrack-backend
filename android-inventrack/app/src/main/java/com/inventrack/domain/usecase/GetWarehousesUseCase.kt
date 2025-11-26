package com.inventrack.domain.usecase

import com.inventrack.core.Resource
import com.inventrack.domain.model.Warehouse
import com.inventrack.domain.repository.WarehouseRepository

class GetWarehousesUseCase(private val repo: WarehouseRepository) {
    suspend operator fun invoke(): Resource<List<Warehouse>> = repo.getWarehouses()
}
