package com.dak0ta.learnity.core.network.data.mapper

import com.dak0ta.learnity.core.domain.student.Student
import com.dak0ta.learnity.core.network.data.api.dto.student.StudentDto

internal fun StudentDto.toDomain(): Student = Student(
    grade = grade,
    parentContact = parentContact,
)

internal fun Student.toDto(): StudentDto = StudentDto(
    grade = grade,
    parentContact = parentContact,
)
