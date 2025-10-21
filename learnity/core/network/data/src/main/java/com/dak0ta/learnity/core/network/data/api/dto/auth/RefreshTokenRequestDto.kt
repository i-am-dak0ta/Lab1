package com.dak0ta.learnity.core.network.data.api.dto.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class RefreshTokenRequestDto(
    @param:Json(name = "refresh_token") val refreshToken: String,
)
