package com.dak0ta.learnity.core.network.data.api.dto.student

import com.dak0ta.learnity.core.network.data.api.dto.user.UserWithRoleDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class StudentsResponseDto(
    @param:Json(name = "students") val students: List<UserWithRoleDto>,
)
