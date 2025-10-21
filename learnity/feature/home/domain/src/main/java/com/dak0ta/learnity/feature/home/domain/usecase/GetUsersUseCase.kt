package com.dak0ta.learnity.feature.home.domain.usecase

import com.dak0ta.learnity.core.domain.user.UserWithRole

interface GetUsersUseCase {

    suspend operator fun invoke(): List<UserWithRole>
}
