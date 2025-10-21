package com.dak0ta.learnity.core.network.data.api.dto.tutor

import com.dak0ta.learnity.core.network.data.api.dto.user.UserWithRoleDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class TutorsResponseDto(
    @param:Json(name = "tutors") val tutors: List<UserWithRoleDto>
)
