package com.dak0ta.learnity.feature.profile.data.repository

import com.dak0ta.learnity.core.coroutine.CoroutineDispatchers
import com.dak0ta.learnity.core.database.domain.cache.CacheManager
import com.dak0ta.learnity.core.database.domain.repository.UserWithRoleLocalRepository
import com.dak0ta.learnity.core.datastore.domain.usecase.userid.GetUserIdUseCase
import com.dak0ta.learnity.core.domain.user.UserWithRole
import com.dak0ta.learnity.core.network.domain.model.ApiResult
import com.dak0ta.learnity.core.network.domain.repository.UserWithRoleRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UserRepositoryImpl @Inject constructor(
    private val local: UserWithRoleLocalRepository,
    private val remote: UserWithRoleRemoteRepository,
    private val cacheManager: CacheManager,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val dispatchers: CoroutineDispatchers,
) : UserRepository {

    override suspend fun getUserMe(forceUpdate: Boolean): UserWithRole = withContext(dispatchers.io) {
        val userId = getUserIdUseCase() ?: error("User ID not found in datastore")

        val isCacheActual = cacheManager.isCacheActual(CACHE_KEY_USER_ME)
        if (!forceUpdate && isCacheActual) {
            local.getUser(userId) ?: fetchAndCacheUser(userId)
        } else {
            fetchAndCacheUser(userId)
        }
    }

    override fun observeUserMeCache(userId: String): Flow<UserWithRole?> {
        return local.observeUser(userId).distinctUntilChanged()
    }

    private suspend fun fetchAndCacheUser(userId: String): UserWithRole {
        return when (val result = remote.getUserById(userId)) {
            is ApiResult.Success -> {
                val user = result.data
                local.upsertUser(user)
                cacheManager.updateCacheTimestamp(CACHE_KEY_USER_ME)
                user
            }

            is ApiResult.Failure -> {
                local.getUser(userId) ?: error("Failed to load user and no cache available")
            }
        }
    }

    private companion object {

        const val CACHE_KEY_USER_ME = "cache_user_me"
    }
}
