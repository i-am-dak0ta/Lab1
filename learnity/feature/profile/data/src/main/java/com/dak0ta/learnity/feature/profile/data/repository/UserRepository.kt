package com.dak0ta.learnity.feature.profile.data.repository

import com.dak0ta.learnity.core.domain.user.UserWithRole
import kotlinx.coroutines.flow.Flow

internal interface UserRepository {

    suspend fun getUserMe(forceUpdate: Boolean = false): UserWithRole
    fun observeUserMeCache(userId: String): Flow<UserWithRole?>
}
