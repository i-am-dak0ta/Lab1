package com.dak0ta.learnity.core.network.data.api.dto.tutor

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class TutorDto(
    @param:Json(name = "bio") val bio: String?,
    @param:Json(name = "subjects") val subjects: List<String>?,
    @param:Json(name = "rating") val rating: Double?,
)
