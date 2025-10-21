package com.dak0ta.learnity.feature.home.data.repository

import com.dak0ta.learnity.core.domain.user.UserWithRole
import kotlinx.coroutines.flow.Flow

internal interface UsersRepository {

    suspend fun getUsers(forceUpdate: Boolean = false): List<UserWithRole>
    fun observeUsersCache(): Flow<List<UserWithRole>>
}
