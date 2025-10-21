package com.dak0ta.learnity.feature.home.data.usecase

import com.dak0ta.learnity.core.domain.user.UserWithRole
import com.dak0ta.learnity.feature.home.data.repository.UsersRepository
import com.dak0ta.learnity.feature.home.domain.usecase.ObserveUsersUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ObserveUsersUseCaseImpl @Inject constructor(
    private val repository: UsersRepository,
) : ObserveUsersUseCase {

    override fun invoke(): Flow<List<UserWithRole>> {
        return repository.observeUsersCache()
    }
}
