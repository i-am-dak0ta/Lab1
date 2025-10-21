package com.dak0ta.learnity.feature.authorization.data.repository

import com.dak0ta.learnity.core.coroutine.CoroutineDispatchers
import com.dak0ta.learnity.core.database.domain.cache.CacheManager
import com.dak0ta.learnity.core.database.domain.repository.UserWithRoleLocalRepository
import com.dak0ta.learnity.core.network.domain.model.ApiResult
import com.dak0ta.learnity.core.network.domain.repository.AuthRemoteRepository
import com.dak0ta.learnity.feature.authorization.data.auth.AuthSessionUpdater
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthorizationRepositoryImpl @Inject constructor(
    private val local: UserWithRoleLocalRepository,
    private val remote: AuthRemoteRepository,
    private val authSessionUpdater: AuthSessionUpdater,
    private val cacheManager: CacheManager,
    private val dispatchers: CoroutineDispatchers,
) : AuthorizationRepository {

    override suspend fun login(input: String, password: String): Unit = withContext(dispatchers.io) {
        when (val result = remote.login(input, password)) {
            is ApiResult.Success -> {
                val data = result.data
                local.upsertUser(data.user)
                cacheManager.updateCacheTimestamp(CACHE_KEY_USER_ME)
                authSessionUpdater.updateAllAsync(
                    scope = this,
                    accessToken = data.accessToken,
                    refreshToken = data.refreshToken,
                    userId = data.user.user.id,
                    userRole = data.user.user.role,
                )
            }

            is ApiResult.Failure -> {
                error("Failed to load user and no cache available")
            }
        }
    }

    private companion object {

        const val CACHE_KEY_USER_ME = "cache_user_me"
    }
}
