package com.inventrack.data.remote.dto

data class ResetPasswordDto(
    val token: String,
    val newPassword: String
)
