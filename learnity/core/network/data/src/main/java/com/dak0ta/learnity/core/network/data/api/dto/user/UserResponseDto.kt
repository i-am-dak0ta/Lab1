package com.dak0ta.learnity.core.network.data.api.dto.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class UserResponseDto(
    @param:Json(name = "user") val userWithRole: UserWithRoleDto,
)
