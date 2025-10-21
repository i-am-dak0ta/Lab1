package com.dak0ta.learnity.core.network.data.repository

import android.util.Log
import com.dak0ta.learnity.core.domain.auth.AuthData
import com.dak0ta.learnity.core.domain.user.UserRole
import com.dak0ta.learnity.core.network.data.api.dto.auth.AuthLoginRequestDto
import com.dak0ta.learnity.core.network.data.api.dto.auth.RefreshTokenRequestDto
import com.dak0ta.learnity.core.network.data.api.dto.auth.RegistrationRequestDto
import com.dak0ta.learnity.core.network.data.api.service.AuthService
import com.dak0ta.learnity.core.network.data.mapper.toDomain
import com.dak0ta.learnity.core.network.data.network.SafeApiCall
import com.dak0ta.learnity.core.network.domain.model.ApiResult
import com.dak0ta.learnity.core.network.domain.model.AuthTokens
import com.dak0ta.learnity.core.network.domain.repository.AuthRemoteRepository

internal class AuthRemoteRepositoryImpl(
    private val service: AuthService,
    private val safeApiCall: SafeApiCall,
) : AuthRemoteRepository {

    override suspend fun login(input: String, password: String): ApiResult<AuthData> {
        Log.d(TAG, "Login")
        return safeApiCall("LOGIN") {
            val response = service.login(AuthLoginRequestDto(input, password))
            AuthData(
                user = response.user.toDomain(),
                accessToken = response.accessToken,
                refreshToken = response.refreshToken,
            )
        }
    }

    override suspend fun registration(email: String, password: String, role: UserRole): ApiResult<Unit> {
        Log.d(TAG, "Registration")
        return safeApiCall("REGISTRATION") {
            service.registration(RegistrationRequestDto(email, password, role))
        }
    }

    override suspend fun refreshToken(refreshToken: String): ApiResult<AuthTokens> {
        Log.d(TAG, "Refreshing token")
        return safeApiCall("REFRESH_TOKEN") {
            val response = service.refresh(RefreshTokenRequestDto(refreshToken))
            AuthTokens(
                accessToken = response.accessToken,
                refreshToken = response.refreshToken,
            )
        }
    }

    private companion object {

        const val TAG = "Learnity:AuthRemoteRepositoryImpl"
    }
}
