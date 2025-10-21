package com.dak0ta.learnity.feature.home.domain.usecase

import com.dak0ta.learnity.core.domain.user.UserWithRole
import kotlinx.coroutines.flow.Flow

interface ObserveUsersUseCase {

    operator fun invoke(): Flow<List<UserWithRole>>
}
