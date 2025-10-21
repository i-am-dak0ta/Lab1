package com.dak0ta.learnity.core.network.data.mapper

import com.dak0ta.learnity.core.domain.user.UserWithRole
import com.dak0ta.learnity.core.network.data.api.dto.student.StudentsResponseDto
import com.dak0ta.learnity.core.network.data.api.dto.tutor.TutorsResponseDto
import com.dak0ta.learnity.core.network.data.api.dto.user.UserResponseDto

internal fun StudentsResponseDto.toDomainList(): List<UserWithRole> =
    students.map { it.toDomain() }

internal fun TutorsResponseDto.toDomainList(): List<UserWithRole> =
    tutors.map { it.toDomain() }

internal fun UserResponseDto.toDomain(): UserWithRole =
    userWithRole.toDomain()
