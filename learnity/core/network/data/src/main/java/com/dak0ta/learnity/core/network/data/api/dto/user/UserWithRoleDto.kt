package com.dak0ta.learnity.core.network.data.api.dto.user

import com.dak0ta.learnity.core.network.data.api.dto.student.StudentDto
import com.dak0ta.learnity.core.network.data.api.dto.tutor.TutorDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class UserWithRoleDto(
    @param:Json(name = "user") val user: UserDto,
    @param:Json(name = "student") val student: StudentDto?,
    @param:Json(name = "tutor") val tutor: TutorDto?,
)
