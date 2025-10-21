package com.dak0ta.learnity.core.domain.auth

import com.dak0ta.learnity.core.domain.user.UserWithRole

data class AuthData(
    val user: UserWithRole,
    val accessToken: String,
    val refreshToken: String,
)
