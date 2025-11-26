package com.inventrack.domain.repository

import com.inventrack.core.Resource
import com.inventrack.domain.model.StockMovement
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun registerEntry(productId:Int, qty:Int, warehouseId:Int?, note:String?): Resource<StockMovement>
    suspend fun registerExit(productId:Int, qty:Int, warehouseId:Int?, note:String?): Resource<StockMovement>
    suspend fun getHistory(): Resource<List<StockMovement>>
}
