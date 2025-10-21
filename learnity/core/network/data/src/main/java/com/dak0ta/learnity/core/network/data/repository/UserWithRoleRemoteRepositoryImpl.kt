package com.dak0ta.learnity.core.network.data.repository

import android.util.Log
import com.dak0ta.learnity.core.domain.user.UserWithRole
import com.dak0ta.learnity.core.network.data.api.service.UserService
import com.dak0ta.learnity.core.network.data.mapper.toDomain
import com.dak0ta.learnity.core.network.data.network.SafeApiCall
import com.dak0ta.learnity.core.network.domain.model.ApiResult
import com.dak0ta.learnity.core.network.domain.repository.UserWithRoleRemoteRepository

internal class UserWithRoleRemoteRepositoryImpl(
    private val service: UserService,
    private val safeApiCall: SafeApiCall,
) : UserWithRoleRemoteRepository {

    override suspend fun getStudents(): ApiResult<List<UserWithRole>> {
        Log.d(TAG, "Getting all students")
        return safeApiCall("GET_STUDENTS") { service.getStudents().students.map { it.toDomain() } }
    }

    override suspend fun getTutors(): ApiResult<List<UserWithRole>> {
        Log.d(TAG, "Getting all tutors")
        return safeApiCall("GET_TUTORS") { service.getTutors().tutors.map { it.toDomain() } }
    }

    override suspend fun getUserById(id: String): ApiResult<UserWithRole> {
        Log.d(TAG, "Getting user by ID: $id")
        return safeApiCall("GET_USER_BY_ID") { service.getUserById(id).userWithRole.toDomain() }
    }

    private companion object {

        const val TAG = "Learnity:UserWithRoleRemoteRepositoryImpl"
    }
}
