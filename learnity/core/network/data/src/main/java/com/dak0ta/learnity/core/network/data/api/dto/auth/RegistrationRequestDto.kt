package com.dak0ta.learnity.core.network.data.api.dto.auth

import com.dak0ta.learnity.core.domain.user.UserRole
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class RegistrationRequestDto(
    @param:Json(name = "email") val email: String,
    @param:Json(name = "password") val password: String,
    @param:Json(name = "role") val role: UserRole,
)
