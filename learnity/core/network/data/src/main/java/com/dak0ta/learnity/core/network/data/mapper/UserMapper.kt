package com.dak0ta.learnity.core.network.data.mapper

import com.dak0ta.learnity.core.domain.user.User
import com.dak0ta.learnity.core.network.data.api.dto.user.UserDto

internal fun UserDto.toDomain(): User = User(
    id = id,
    email = email,
    nickname = nickname,
    fullName = fullName,
    age = age,
    timeZone = timeZone,
    role = role,
    createdAt = createdAt,
    updatedAt = updatedAt,
)

internal fun User.toDto(): UserDto = UserDto(
    id = id,
    email = email,
    nickname = nickname,
    fullName = fullName,
    age = age,
    timeZone = timeZone,
    role = role,
    createdAt = createdAt,
    updatedAt = updatedAt,
)
