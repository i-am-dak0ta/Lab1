package com.dak0ta.learnity.core.network.domain.repository

import com.dak0ta.learnity.core.domain.user.UserWithRole
import com.dak0ta.learnity.core.network.domain.model.ApiResult

interface UserWithRoleRemoteRepository {

    suspend fun getStudents(): ApiResult<List<UserWithRole>>
    suspend fun getTutors(): ApiResult<List<UserWithRole>>
    suspend fun getUserById(id: String): ApiResult<UserWithRole>
}
