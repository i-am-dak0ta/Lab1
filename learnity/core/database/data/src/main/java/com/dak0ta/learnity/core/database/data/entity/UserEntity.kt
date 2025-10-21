package com.dak0ta.learnity.core.database.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dak0ta.learnity.core.domain.user.UserRole

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val email: String,
    val nickname: String?,
    @ColumnInfo(name = "fullname") val fullName: String?,
    val age: Int?,
    @ColumnInfo(name = "time_zone") val timeZone: String?,
    val role: UserRole,
    @ColumnInfo(name = "created_at") val createdAt: String,
    @ColumnInfo(name = "updated_at") val updatedAt: String,
)
