package com.dak0ta.learnity.core.domain.course

data class Course(
    val id: String,
    val tutorId: String,
    val title: String?,
    val subject: String?,
    val level: CourseLevel,
    val status: String?,
)
