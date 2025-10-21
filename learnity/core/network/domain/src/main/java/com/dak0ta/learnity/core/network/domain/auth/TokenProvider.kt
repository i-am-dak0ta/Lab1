package com.dak0ta.learnity.core.network.domain.auth

interface TokenProvider {

    suspend fun getAccessToken(): String?
}
