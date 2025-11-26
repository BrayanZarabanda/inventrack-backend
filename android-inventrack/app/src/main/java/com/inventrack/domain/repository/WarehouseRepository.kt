package com.inventrack.domain.repository

import com.inventrack.core.Resource
import com.inventrack.domain.model.Warehouse
import kotlinx.coroutines.flow.Flow

interface WarehouseRepository {
    suspend fun getWarehouses(): Resource<List<Warehouse>>
    suspend fun createWarehouse(name:String, address:String?): Resource<Warehouse>
    suspend fun updateWarehouse(id:Int, name:String, address:String?): Resource<Warehouse>
    suspend fun deleteWarehouse(id:Int): Resource<Unit>
}
