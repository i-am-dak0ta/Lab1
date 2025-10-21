package com.dak0ta.learnity.core.database.data.mapper

import com.dak0ta.learnity.core.database.data.entity.CourseEntity
import com.dak0ta.learnity.core.domain.course.Course

internal fun CourseEntity.toDomain(): Course = Course(
    id = id,
    tutorId = tutorId,
    title = title,
    subject = subject,
    level = level,
    status = status,
)

internal fun Course.toEntity(): CourseEntity = CourseEntity(
    id = id,
    tutorId = tutorId,
    title = title,
    subject = subject,
    level = level,
    status = status,
)
