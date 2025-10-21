package com.dak0ta.learnity.core.network.data.converter

import com.dak0ta.learnity.core.domain.user.UserRole
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

internal class UserRoleConverter {

    @FromJson
    fun fromJson(value: String): UserRole = when (value.lowercase()) {
        "tutor" -> UserRole.TUTOR
        "student" -> UserRole.STUDENT
        "admin" -> UserRole.ADMIN
        else -> UserRole.STUDENT
    }

    @ToJson
    fun toJson(role: UserRole): String = when (role) {
        UserRole.TUTOR -> "tutor"
        UserRole.STUDENT -> "student"
        UserRole.ADMIN -> "admin"
    }
}
