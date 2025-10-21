package com.dak0ta.learnity.core.network.data.api.dto.student

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class StudentDto(
    @param:Json(name = "grade") val grade: String?,
    @param:Json(name = "parent_contact") val parentContact: String?,
)
