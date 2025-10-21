package com.dak0ta.learnity.feature.profile.presentation.ui

internal data class UserInfo(
    val nickname: String,
    val fullName: String,
    val email: String,
    val age: String,
    val timeZone: String,
    val createdAt: String,
    val role: String,
    val roleInfo: RoleInfo,
)
