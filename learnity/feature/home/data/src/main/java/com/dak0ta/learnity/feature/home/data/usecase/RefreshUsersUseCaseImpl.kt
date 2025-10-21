package com.dak0ta.learnity.feature.home.data.usecase

import com.dak0ta.learnity.feature.home.data.repository.UsersRepository
import com.dak0ta.learnity.feature.home.domain.usecase.RefreshUsersUseCase
import javax.inject.Inject

internal class RefreshUsersUseCaseImpl @Inject constructor(
    private val repository: UsersRepository,
) : RefreshUsersUseCase {

    override suspend fun invoke() {
        repository.getUsers(forceUpdate = true)
    }
}
