package com.inventrack.data.remote.dto

import com.inventrack.domain.model.User

fun UserDto.toDomain(): User = User(
    id = id,
    email = email,
    name = name,
    role = role
)

fun User.toDto(): UserDto = UserDto(id = id, email = email, name = name, role = role)
