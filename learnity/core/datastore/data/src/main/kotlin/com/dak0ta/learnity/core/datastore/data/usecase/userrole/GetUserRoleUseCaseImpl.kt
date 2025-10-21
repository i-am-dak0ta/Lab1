package com.dak0ta.learnity.core.datastore.data.usecase.userrole

import com.dak0ta.learnity.core.datastore.data.repository.UserPreferencesRepository
import com.dak0ta.learnity.core.datastore.domain.usecase.userrole.GetUserRoleUseCase
import com.dak0ta.learnity.core.domain.user.UserRole
import kotlinx.coroutines.flow.first
import javax.inject.Inject

internal class GetUserRoleUseCaseImpl @Inject constructor(
    private val repository: UserPreferencesRepository,
) : GetUserRoleUseCase {

    override suspend fun invoke(): UserRole = repository.userRoleFlow.first()
}
