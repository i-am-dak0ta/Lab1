package com.dak0ta.learnity.core.domain.user

import com.dak0ta.learnity.core.domain.student.Student
import com.dak0ta.learnity.core.domain.tutor.Tutor

data class UserWithRole(
    val user: User,
    val student: Student?,
    val tutor: Tutor?,
)
