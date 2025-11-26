package com.inventrack.domain.usecase

import com.inventrack.core.Resource
import com.inventrack.domain.model.Warehouse
import com.inventrack.domain.repository.WarehouseRepository

class CreateWarehouseUseCase(private val repo: WarehouseRepository) {
    suspend operator fun invoke(name:String, address:String?): Resource<Warehouse> = repo.createWarehouse(name, address)
}
