package com.dak0ta.learnity.feature.profile.domain.usecase

import com.dak0ta.learnity.core.domain.user.UserWithRole

interface GetUserMeUseCase {

    suspend operator fun invoke(): UserWithRole
}
