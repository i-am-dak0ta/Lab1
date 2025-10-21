package com.dak0ta.learnity.core.network.data.api.dto.course

import com.dak0ta.learnity.core.domain.course.CourseLevel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class CourseDto(
    @param:Json(name = "id") val id: String,
    @param:Json(name = "tutor_id") val tutorId: String,
    @param:Json(name = "title") val title: String?,
    @param:Json(name = "subject") val subject: String?,
    @param:Json(name = "level") val level: CourseLevel,
    @param:Json(name = "status") val status: String?,
)
