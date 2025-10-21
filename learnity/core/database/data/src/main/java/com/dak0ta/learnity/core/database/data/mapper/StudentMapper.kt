package com.dak0ta.learnity.core.database.data.mapper

import com.dak0ta.learnity.core.database.data.entity.StudentEntity
import com.dak0ta.learnity.core.domain.student.Student

internal fun StudentEntity.toDomain(): Student = Student(
    grade = grade,
    parentContact = parentContact,
)

internal fun Student.toEntity(userId: String): StudentEntity = StudentEntity(
    id = userId,
    grade = grade,
    parentContact = parentContact,
)
