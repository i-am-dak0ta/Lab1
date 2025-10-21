package com.dak0ta.learnity.core.network.data.adapter

import com.dak0ta.learnity.core.coroutine.CoroutineDispatchers
import com.dak0ta.learnity.core.datastore.domain.usecase.accesstoken.GetAccessTokenUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.accesstoken.UpdateAccessTokenUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.refreshtoken.GetRefreshTokenUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.refreshtoken.UpdateRefreshTokenUseCase
import com.dak0ta.learnity.core.network.domain.auth.AccessTokenRefresher
import com.dak0ta.learnity.core.network.domain.auth.TokenResetter
import com.dak0ta.learnity.core.network.domain.model.ApiResult
import com.dak0ta.learnity.core.network.domain.repository.AuthRemoteRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

// TODO detekt - LongParameterList
@Suppress("LongParameterList")
@Singleton
internal class AccessTokenRefresherAdapter @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getRefreshTokenUseCase: GetRefreshTokenUseCase,
    private val updateAccessTokenUseCase: UpdateAccessTokenUseCase,
    private val updateRefreshTokenUseCase: UpdateRefreshTokenUseCase,
    private val authRemoteRepository: AuthRemoteRepository,
    private val tokenResetter: TokenResetter,
    private val dispatchers: CoroutineDispatchers,
) : AccessTokenRefresher {

    private val mutex = Mutex()

    override suspend fun refreshAccessToken(): String? = withContext(dispatchers.io) {
        val current = getAccessTokenUseCase()
        if (!current.isNullOrBlank()) return@withContext current

        mutex.withLock {
            val now = getAccessTokenUseCase()
            if (!now.isNullOrBlank()) return@withContext now

            val refreshToken = getRefreshTokenUseCase() ?: return@withContext null

            when (val res = authRemoteRepository.refreshToken(refreshToken)) {
                is ApiResult.Success -> {
                    val payload = res.data
                    updateAccessTokenUseCase(payload.accessToken)
                    payload.refreshToken?.let { updateRefreshTokenUseCase(it) }
                    payload.accessToken
                }

                is ApiResult.Failure -> {
                    tokenResetter.resetToken()
                    null
                }
            }
        }
    }
}
