package com.dak0ta.learnity.core.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.dak0ta.learnity.core.database.data.entity.UserEntity
import com.dak0ta.learnity.core.database.data.entity.UserWithRoleEntity
import kotlinx.coroutines.flow.Flow

// TODO detekt - TooManyFunctions
@Suppress("TooManyFunctions")
@Dao
internal interface UserDao {

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    fun getByIdFlow(id: String): Flow<UserEntity?>

    @Query("SELECT * FROM users")
    fun getAllFlow(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    suspend fun getByIdOnce(id: String): UserEntity?

    @Query("SELECT * FROM users")
    suspend fun getAllOnce(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserEntity>)

    @Query("DELETE FROM users WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM users")
    suspend fun deleteAll()

    @Transaction
    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    fun getUserWithRoleFlow(id: String): Flow<UserWithRoleEntity?>

    @Transaction
    @Query("SELECT * FROM users")
    fun getAllUsersWithRolesFlow(): Flow<List<UserWithRoleEntity>>

    @Transaction
    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    suspend fun getUserWithRoleOnce(id: String): UserWithRoleEntity?

    @Transaction
    @Query("SELECT * FROM users")
    suspend fun getAllUsersWithRolesOnce(): List<UserWithRoleEntity>
}
