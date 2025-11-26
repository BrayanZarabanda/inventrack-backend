package com.inventrack.domain.usecase

import com.inventrack.core.Resource
import com.inventrack.domain.model.Product
import com.inventrack.domain.repository.ProductRepository

class UpdateProductUseCase(private val repo: ProductRepository) {
    suspend operator fun invoke(id: Int, product: Product): Resource<Product> = repo.updateProduct(id, product)
}
