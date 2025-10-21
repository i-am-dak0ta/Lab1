package com.dak0ta.learnity.core.database.domain.repository

import com.dak0ta.learnity.core.domain.user.UserWithRole
import kotlinx.coroutines.flow.Flow

// TODO detekt - TooManyFunctions
@Suppress("TooManyFunctions")
interface UserWithRoleLocalRepository {

    fun observeUser(id: String): Flow<UserWithRole?>
    fun observeStudents(): Flow<List<UserWithRole>>
    fun observeTutors(): Flow<List<UserWithRole>>
    fun observeUsers(): Flow<List<UserWithRole>>
    suspend fun getStudents(): List<UserWithRole>
    suspend fun getTutors(): List<UserWithRole>
    suspend fun getUsers(): List<UserWithRole>
    suspend fun getUser(id: String): UserWithRole?
    suspend fun upsertUser(userWithRole: UserWithRole)
    suspend fun upsertUsers(usersWithRole: List<UserWithRole>)
    suspend fun deleteUser(id: String)
    suspend fun deleteUsers()
}
