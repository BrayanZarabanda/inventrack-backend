package com.inventrack.data.remote.dto

data class UserDto(
    val id: Int,
    val email: String,
    val name: String?,
    val role: String
)
