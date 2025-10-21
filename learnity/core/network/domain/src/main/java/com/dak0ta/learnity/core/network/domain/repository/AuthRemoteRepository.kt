package com.dak0ta.learnity.core.network.domain.repository

import com.dak0ta.learnity.core.domain.auth.AuthData
import com.dak0ta.learnity.core.domain.user.UserRole
import com.dak0ta.learnity.core.network.domain.model.ApiResult
import com.dak0ta.learnity.core.network.domain.model.AuthTokens

interface AuthRemoteRepository {

    suspend fun login(input: String, password: String): ApiResult<AuthData>
    suspend fun registration(email: String, password: String, role: UserRole): ApiResult<Unit>
    suspend fun refreshToken(refreshToken: String): ApiResult<AuthTokens>
}
