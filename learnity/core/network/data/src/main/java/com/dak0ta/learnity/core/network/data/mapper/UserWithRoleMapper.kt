package com.dak0ta.learnity.core.network.data.mapper

import com.dak0ta.learnity.core.domain.user.UserWithRole
import com.dak0ta.learnity.core.network.data.api.dto.user.UserWithRoleDto

internal fun UserWithRoleDto.toDomain(): UserWithRole = UserWithRole(
    user = user.toDomain(),
    student = student?.toDomain(),
    tutor = tutor?.toDomain(),
)
