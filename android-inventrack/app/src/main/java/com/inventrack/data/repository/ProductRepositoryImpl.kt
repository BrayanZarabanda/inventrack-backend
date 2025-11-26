package com.inventrack.data.repository

import com.inventrack.core.Resource
import com.inventrack.data.local.db.AppDatabase
import com.inventrack.data.remote.ApiService
import com.inventrack.data.remote.dto.CreateProductDto
import com.inventrack.data.remote.dto.UpdateProductDto
import com.inventrack.data.remote.dto.toDomain
import com.inventrack.data.remote.dto.toEntity
import com.inventrack.domain.model.Product
import com.inventrack.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val db: AppDatabase
) : ProductRepository {
    private val dao = db.productDao()

    override fun getProductsLocal(): Flow<List<Product>> = dao.getAll().map { list -> list.map { it.toDomain() } }

    override suspend fun fetchProductsRemote(query: String?): Resource<List<Product>> {
        val resp = api.getProducts(query)
        return if (resp.isSuccessful) {
            val body = resp.body() ?: emptyList()
            dao.insertAll(body.map { it.toEntity() })
            Resource.Success(body.map { it.toDomain() })
        } else {
            Resource.Error(resp.message())
        }
    }

    override suspend fun getProduct(id: Int): Resource<Product> {
        val resp = api.getProduct(id)
        return if (resp.isSuccessful) {
            Resource.Success(resp.body()!!.toDomain())
        } else {
            val local = dao.getById(id)
            if (local != null) Resource.Success(local.toDomain()) else Resource.Error(resp.message())
        }
    }

    override suspend fun createProduct(product: Product): Resource<Product> {
        val dto = CreateProductDto.fromDomain(product)
        val resp = api.createProduct(dto)
        return if (resp.isSuccessful) {
            val created = resp.body()!!
            dao.insert(created.toEntity())
            Resource.Success(created.toDomain())
        } else Resource.Error(resp.message())
    }

    override suspend fun updateProduct(id: Int, product: Product): Resource<Product> {
        val dto = UpdateProductDto.fromDomain(product)
        val resp = api.updateProduct(id, dto)
        return if (resp.isSuccessful) {
            val updated = resp.body()!!
            dao.insert(updated.toEntity())
            Resource.Success(updated.toDomain())
        } else Resource.Error(resp.message())
    }

    override suspend fun deleteProduct(id: Int): Resource<Unit> {
        val resp = api.deleteProduct(id)
        return if (resp.isSuccessful) {
            dao.delete(id)
            Resource.Success(Unit)
        } else Resource.Error(resp.message())
    }
}
