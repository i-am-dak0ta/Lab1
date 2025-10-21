package com.dak0ta.learnity.core.database.data.mapper

import com.dak0ta.learnity.core.database.data.entity.UserEntity
import com.dak0ta.learnity.core.domain.user.User

internal fun UserEntity.toDomain(): User = User(
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

internal fun User.toEntity(): UserEntity = UserEntity(
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
