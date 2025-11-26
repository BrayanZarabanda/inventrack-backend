package com.inventrack.domain.usecase

import com.inventrack.core.Resource
import com.inventrack.domain.model.Product
import com.inventrack.domain.repository.ProductRepository

class CreateProductUseCase(private val repo: ProductRepository) {
    suspend operator fun invoke(product: Product): Resource<Product> = repo.createProduct(product)
}
