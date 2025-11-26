package com.inventrack.domain.usecase

import com.inventrack.core.Resource
import com.inventrack.domain.model.StockMovement
import com.inventrack.domain.repository.StockRepository

class RegisterExitUseCase(private val repo: StockRepository) {
    suspend operator fun invoke(productId:Int, qty:Int, warehouseId:Int?, note:String?): Resource<StockMovement> = repo.registerExit(productId, qty, warehouseId, note)
}
