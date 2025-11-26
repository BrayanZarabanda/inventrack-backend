package com.inventrack.data.repository

import com.inventrack.core.Resource
import com.inventrack.data.remote.ApiService
import com.inventrack.data.remote.dto.CreateWarehouseDto
import com.inventrack.data.remote.dto.WarehouseDto
import com.inventrack.domain.model.Warehouse
import com.inventrack.domain.repository.WarehouseRepository
import javax.inject.Inject

class WarehouseRepositoryImpl @Inject constructor(
    private val api: ApiService
) : WarehouseRepository {
    override suspend fun getWarehouses(): Resource<List<Warehouse>> {
        val resp = api.getWarehouses()
        return if (resp.isSuccessful) {
            val body = resp.body() ?: emptyList()
            Resource.Success(body.map { Warehouse(it.id, it.name, it.address) })
        } else Resource.Error(resp.message())
    }

    override suspend fun createWarehouse(name: String, address: String?): Resource<Warehouse> {
        val dto = CreateWarehouseDto(name, address)
        val resp = api.createWarehouse(dto)
        return if (resp.isSuccessful) {
            val b = resp.body()!!
            Resource.Success(Warehouse(b.id, b.name, b.address))
        } else Resource.Error(resp.message())
    }

    override suspend fun updateWarehouse(id: Int, name: String, address: String?): Resource<Warehouse> {
        val dto = CreateWarehouseDto(name, address)
        val resp = api.updateWarehouse(id, dto)
        return if (resp.isSuccessful) {
            val b = resp.body()!!
            Resource.Success(Warehouse(b.id, b.name, b.address))
        } else Resource.Error(resp.message())
    }

    override suspend fun deleteWarehouse(id: Int): Resource<Unit> {
        val resp = api.deleteWarehouse(id)
        return if (resp.isSuccessful) Resource.Success(Unit) else Resource.Error(resp.message())
    }
}
