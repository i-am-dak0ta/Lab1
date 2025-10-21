package com.dak0ta.learnity.core.network.data.mapper

import com.dak0ta.learnity.core.domain.course.Course
import com.dak0ta.learnity.core.network.data.api.dto.course.CourseDto

internal fun CourseDto.toDomain(): Course = Course(
    id = id,
    tutorId = tutorId,
    title = title,
    subject = subject,
    level = level,
    status = status,
)

internal fun Course.toDto(): CourseDto = CourseDto(
    id = id,
    tutorId = tutorId,
    title = title,
    subject = subject,
    level = level,
    status = status,
)
