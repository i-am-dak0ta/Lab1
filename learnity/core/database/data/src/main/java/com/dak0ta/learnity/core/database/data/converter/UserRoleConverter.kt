package com.dak0ta.learnity.core.database.data.converter

import androidx.room.TypeConverter
import com.dak0ta.learnity.core.domain.user.UserRole

internal class UserRoleConverter {

    @TypeConverter
    fun fromUserRole(role: UserRole): String = role.name.lowercase()

    @TypeConverter
    fun toUserRole(role: String): UserRole = UserRole.valueOf(role.uppercase())
}
