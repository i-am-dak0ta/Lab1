package com.dak0ta.learnity.core.network.data.api.service

import com.dak0ta.learnity.core.network.data.api.dto.auth.AuthLoginRequestDto
import com.dak0ta.learnity.core.network.data.api.dto.auth.AuthLoginResponseDto
import com.dak0ta.learnity.core.network.data.api.dto.auth.RefreshTokenRequestDto
import com.dak0ta.learnity.core.network.data.api.dto.auth.RefreshTokenResponseDto
import com.dak0ta.learnity.core.network.data.api.dto.auth.RegistrationRequestDto
import com.dak0ta.learnity.core.network.data.api.dto.auth.RegistrationResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

internal interface AuthService {

    @POST("login")
    suspend fun login(@Body request: AuthLoginRequestDto): AuthLoginResponseDto

    @POST("registration")
    suspend fun registration(@Body request: RegistrationRequestDto): RegistrationResponseDto

    @POST("refresh")
    suspend fun refresh(@Body request: RefreshTokenRequestDto): RefreshTokenResponseDto
}
