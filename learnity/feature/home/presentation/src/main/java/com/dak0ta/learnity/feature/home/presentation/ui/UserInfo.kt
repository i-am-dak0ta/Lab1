package com.dak0ta.learnity.feature.home.presentation.ui

internal data class UserInfo(
    val id: String,
    val nickname: String,
    val fullName: String,
    val age: String,
    val role: String,
    val roleInfo: RoleInfo,
)
