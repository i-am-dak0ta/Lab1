package com.dak0ta.learnity.core.network.data.api.dto.user

import com.dak0ta.learnity.core.domain.user.UserRole
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class UserDto(
    @param:Json(name = "id") val id: String,
    @param:Json(name = "email") val email: String,
    @param:Json(name = "nickname") val nickname: String?,
    @param:Json(name = "fullname") val fullName: String?,
    @param:Json(name = "age") val age: Int?,
    @param:Json(name = "time_zone") val timeZone: String?,
    @param:Json(name = "role") val role: UserRole,
    @param:Json(name = "created_at") val createdAt: String,
    @param:Json(name = "updated_at") val updatedAt: String,
)
