package com.inventrack.data.remote.dto

data class AuthResponseDto(
    val access_token: String,
    val refresh_token: String?,
    val token_type: String = "bearer",
    val user: UserDto
)
