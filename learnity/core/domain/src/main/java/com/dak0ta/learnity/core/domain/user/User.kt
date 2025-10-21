package com.dak0ta.learnity.core.domain.user

data class User(
    val id: String,
    val email: String,
    val nickname: String?,
    val fullName: String?,
    val age: Int?,
    val timeZone: String?,
    val role: UserRole,
    val createdAt: String,
    val updatedAt: String,
)
