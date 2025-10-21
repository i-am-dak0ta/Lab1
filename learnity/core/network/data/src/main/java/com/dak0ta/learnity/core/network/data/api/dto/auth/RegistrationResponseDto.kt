package com.dak0ta.learnity.core.network.data.api.dto.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class RegistrationResponseDto(
    @param:Json(name = "message") val message: String,
)
