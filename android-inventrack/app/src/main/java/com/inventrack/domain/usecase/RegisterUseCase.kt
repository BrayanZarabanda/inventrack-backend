package com.inventrack.domain.usecase

import com.inventrack.core.Resource
import com.inventrack.domain.model.User
import com.inventrack.domain.repository.AuthRepository

class RegisterUseCase(private val repo: AuthRepository) {
    suspend operator fun invoke(name: String?, email: String, password: String): Resource<User> = repo.register(name, email, password)
}
