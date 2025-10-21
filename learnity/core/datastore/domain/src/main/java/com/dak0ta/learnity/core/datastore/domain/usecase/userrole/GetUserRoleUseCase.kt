package com.dak0ta.learnity.core.datastore.domain.usecase.userrole

import com.dak0ta.learnity.core.domain.user.UserRole

interface GetUserRoleUseCase {

    suspend operator fun invoke(): UserRole
}
