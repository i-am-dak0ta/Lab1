package com.dak0ta.learnity.core.datastore.data.mapper

import com.dak0ta.learnity.core.datastore.proto.UserRoleProto
import com.dak0ta.learnity.core.domain.user.UserRole

internal fun protoToDomainRole(proto: UserRoleProto): UserRole =
    when (proto) {
        UserRoleProto.STUDENT -> UserRole.STUDENT
        UserRoleProto.ADMIN -> UserRole.ADMIN
        else -> UserRole.TUTOR
    }

internal fun domainToProtoRole(role: UserRole): UserRoleProto =
    when (role) {
        UserRole.TUTOR -> UserRoleProto.TUTOR
        UserRole.STUDENT -> UserRoleProto.STUDENT
        UserRole.ADMIN -> UserRoleProto.ADMIN
    }
