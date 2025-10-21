package com.dak0ta.learnity.core.network.data.api.dto.auth

import com.dak0ta.learnity.core.network.data.api.dto.user.UserWithRoleDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class AuthLoginResponseDto(
    @param:Json(name = "access_token") val accessToken: String,
    @param:Json(name = "refresh_token") val refreshToken: String,
    @param:Json(name = "message") val message: String,
    @param:Json(name = "status") val status: String,
    @param:Json(name = "time") val time: String,
    @param:Json(name = "user") val user: UserWithRoleDto,
)
