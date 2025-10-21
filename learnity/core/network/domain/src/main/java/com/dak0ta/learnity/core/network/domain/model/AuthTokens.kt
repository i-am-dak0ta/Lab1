package com.dak0ta.learnity.core.network.domain.model

data class AuthTokens(
    val accessToken: String,
    val refreshToken: String? = null,
)
