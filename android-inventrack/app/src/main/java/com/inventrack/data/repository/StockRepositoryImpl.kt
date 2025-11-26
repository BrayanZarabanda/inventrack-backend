package com.inventrack.data.repository

import com.inventrack.core.Resource
import com.inventrack.data.remote.ApiService
import com.inventrack.data.remote.dto.CreateStockDto
import com.inventrack.data.remote.dto.StockMovementDto
import com.inventrack.domain.model.StockMovement
import com.inventrack.domain.repository.StockRepository
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val api: ApiService
) : StockRepository {
    override suspend fun registerEntry(productId: Int, qty: Int, warehouseId: Int?, note: String?): Resource<StockMovement> {
        val dto = CreateStockDto(productId, qty, warehouseId, note)
        val resp = api.registerEntry(dto)
        return if (resp.isSuccessful) {
            val b = resp.body()!!
            Resource.Success(StockMovement(b.id, b.productId, b.productName, b.quantity, b.type, b.warehouseId, b.createdAt))
        } else Resource.Error(resp.message())
    }

    override suspend fun registerExit(productId: Int, qty: Int, warehouseId: Int?, note: String?): Resource<StockMovement> {
        val dto = CreateStockDto(productId, qty, warehouseId, note)
        val resp = api.registerExit(dto)
        return if (resp.isSuccessful) {
            val b = resp.body()!!
            Resource.Success(StockMovement(b.id, b.productId, b.productName, b.quantity, b.type, b.warehouseId, b.createdAt))
        } else Resource.Error(resp.message())
    }

    override suspend fun getHistory(): Resource<List<StockMovement>> {
        val resp = api.getStockHistory()
        return if (resp.isSuccessful) {
            val body = resp.body() ?: emptyList()
            Resource.Success(body.map { StockMovement(it.id, it.productId, it.productName, it.quantity, it.type, it.warehouseId, it.createdAt) })
        } else Resource.Error(resp.message())
    }
}
