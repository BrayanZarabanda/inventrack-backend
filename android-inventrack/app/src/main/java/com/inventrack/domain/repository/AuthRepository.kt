package com.inventrack.domain.repository

import com.inventrack.core.Resource
import com.inventrack.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Resource<User>
    suspend fun register(name: String?, email: String, password: String): Resource<User>
    fun currentUserFlow(): Flow<User?>
    suspend fun fetchProfile(): Resource<User>
    suspend fun logout()
}
package com.inventrack.domain.repository

import com.inventrack.domain.model.User
import com.inventrack.core.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Resource<User>
    suspend fun register(name: String?, email: String, password: String): Resource<User>
    fun currentUserFlow(): Flow<User?>
    suspend fun logout()
}
