package com.dak0ta.learnity.feature.profile.domain.usecase

import com.dak0ta.learnity.core.domain.user.UserWithRole
import kotlinx.coroutines.flow.Flow

interface ObserveUserMeUseCase {

    operator fun invoke(userId: String): Flow<UserWithRole?>
}
