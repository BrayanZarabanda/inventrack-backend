package com.inventrack.domain.usecase

import com.inventrack.core.Resource
import com.inventrack.domain.model.StockMovement
import com.inventrack.domain.repository.StockRepository

class GetStockHistoryUseCase(private val repo: StockRepository) {
    suspend operator fun invoke(): Resource<List<StockMovement>> = repo.getHistory()
}
