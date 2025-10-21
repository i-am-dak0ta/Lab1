package com.dak0ta.learnity.core.network.data.api.dto.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class AuthLoginRequestDto(
    @param:Json(name = "input") val input: String,
    @param:Json(name = "password") val password: String,
)
