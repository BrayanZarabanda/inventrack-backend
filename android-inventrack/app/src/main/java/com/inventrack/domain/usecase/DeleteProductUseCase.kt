package com.inventrack.domain.usecase

import com.inventrack.core.Resource
import com.inventrack.domain.repository.ProductRepository

class DeleteProductUseCase(private val repo: ProductRepository) {
    suspend operator fun invoke(id: Int): Resource<Unit> = repo.deleteProduct(id)
}
