package com.dak0ta.learnity.core.network.domain.auth

interface AccessTokenRefresher {

    suspend fun refreshAccessToken(): String?
}
