package com.inventrack.domain.repository

import com.inventrack.core.Resource
import com.inventrack.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProductsLocal(): Flow<List<Product>>
    suspend fun fetchProductsRemote(query:String? = null): Resource<List<Product>>
    suspend fun getProduct(id: Int): Resource<Product>
    suspend fun createProduct(product: Product): Resource<Product>
    suspend fun updateProduct(id:Int, product: Product): Resource<Product>
    suspend fun deleteProduct(id:Int): Resource<Unit>
}
