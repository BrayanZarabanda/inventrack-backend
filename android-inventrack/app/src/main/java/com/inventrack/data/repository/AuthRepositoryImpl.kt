package com.inventrack.data.repository

import com.inventrack.core.DataStoreManager
import com.inventrack.core.Resource
import com.inventrack.data.remote.ApiService
import com.inventrack.data.remote.dto.LoginRequestDto
import com.inventrack.data.remote.dto.RegisterRequestDto
import com.inventrack.data.remote.dto.toDomain
import com.inventrack.domain.model.User
import com.inventrack.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dataStore: DataStoreManager
) : AuthRepository {

    override suspend fun login(email: String, password: String): Resource<User> {
        val resp = api.login(LoginRequestDto(email, password))
        return if (resp.isSuccessful) {
            val body = resp.body()!!
            dataStore.saveToken(body.access_token)
            body.refresh_token?.let { dataStore.saveRefreshToken(it) }
            dataStore.saveRole(body.user.role)
            Resource.Success(body.user.toDomain())
        } else {
            Resource.Error(resp.message())
        }
    }

    override suspend fun register(name: String?, email: String, password: String): Resource<User> {
        val resp = api.register(RegisterRequestDto(name, email, password))
        return if (resp.isSuccessful) {
            val body = resp.body()!!
            dataStore.saveToken(body.access_token)
            body.refresh_token?.let { dataStore.saveRefreshToken(it) }
            dataStore.saveRole(body.user.role)
            Resource.Success(body.user.toDomain())
        } else {
            Resource.Error(resp.message())
        }
    }

    override fun currentUserFlow(): Flow<User?> {
        return flowOf(null)
    }

    override suspend fun fetchProfile(): Resource<User> {
        val resp = api.profile()
        return if (resp.isSuccessful) {
            val body = resp.body()!!
            Resource.Success(body.toDomain())
        } else Resource.Error(resp.message())
    }

    override suspend fun logout() {
        dataStore.clear()
    }
}
