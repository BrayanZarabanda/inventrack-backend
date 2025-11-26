package com.inventrack.domain.usecase

import com.inventrack.core.Resource
import com.inventrack.domain.model.Product
import com.inventrack.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(private val repo: ProductRepository) {
    fun local(): Flow<List<Product>> = repo.getProductsLocal()
    suspend fun remote(query:String?=null): Resource<List<Product>> = repo.fetchProductsRemote(query)
}
