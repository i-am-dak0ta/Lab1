package com.dak0ta.learnity.feature.home.data.repository

import com.dak0ta.learnity.core.coroutine.CoroutineDispatchers
import com.dak0ta.learnity.core.database.domain.cache.CacheManager
import com.dak0ta.learnity.core.database.domain.repository.UserWithRoleLocalRepository
import com.dak0ta.learnity.core.datastore.domain.usecase.userrole.GetUserRoleUseCase
import com.dak0ta.learnity.core.domain.user.UserRole
import com.dak0ta.learnity.core.domain.user.UserWithRole
import com.dak0ta.learnity.core.network.domain.model.ApiResult
import com.dak0ta.learnity.core.network.domain.repository.UserWithRoleRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UsersRepositoryImpl @Inject constructor(
    private val local: UserWithRoleLocalRepository,
    private val remote: UserWithRoleRemoteRepository,
    private val cacheManager: CacheManager,
    private val getUserRoleUseCase: GetUserRoleUseCase,
    private val dispatchers: CoroutineDispatchers,
) : UsersRepository {

    override suspend fun getUsers(forceUpdate: Boolean): List<UserWithRole> = withContext(dispatchers.io) {
        val role = getUserRoleUseCase()

        val isCacheActual = cacheManager.isCacheActual(CACHE_KEY_USER_LIST)
        if (!forceUpdate && isCacheActual) {
            when (role) {
                UserRole.TUTOR -> local.getStudents()
                UserRole.STUDENT -> local.getTutors()
                UserRole.ADMIN -> local.getStudents()
            }
        } else {
            fetchAndCacheUsers(role)
        }
    }

    override fun observeUsersCache(): Flow<List<UserWithRole>> {
        return flow {
            val role = getUserRoleUseCase()

            val flow: Flow<List<UserWithRole>> = when (role) {
                UserRole.TUTOR -> local.observeStudents()
                UserRole.STUDENT -> local.observeTutors()
                UserRole.ADMIN -> local.observeUsers()
            }

            emitAll(flow.map { it.toList() }.distinctUntilChanged())
        }
    }

    private suspend fun fetchAndCacheUsers(role: UserRole): List<UserWithRole> {
        val result = when (role) {
            UserRole.TUTOR -> remote.getStudents()
            UserRole.STUDENT -> remote.getTutors()
            UserRole.ADMIN -> remote.getStudents()
        }

        return when (result) {
            is ApiResult.Success -> {
                val users = result.data
                local.upsertUsers(users)
                cacheManager.updateCacheTimestamp(CACHE_KEY_USER_LIST)
                users
            }

            is ApiResult.Failure -> {
                val cached = local.getUsers()
                cached.ifEmpty { error("Failed to load users and no cache available") }
            }
        }
    }

    private companion object {

        const val CACHE_KEY_USER_LIST = "cache_user_list"
    }
}
