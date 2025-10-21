package com.dak0ta.learnity.feature.home.data.usecase

import com.dak0ta.learnity.core.domain.user.UserWithRole
import com.dak0ta.learnity.feature.home.data.repository.UsersRepository
import com.dak0ta.learnity.feature.home.domain.usecase.GetUsersUseCase
import javax.inject.Inject

internal class GetUsersUseCaseImpl @Inject constructor(
    private val repository: UsersRepository,
) : GetUsersUseCase {

    override suspend fun invoke(): List<UserWithRole> {
        return repository.getUsers()
    }
}
