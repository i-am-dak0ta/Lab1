package com.dak0ta.learnity.core.datastore.data.usecase.userrole

import com.dak0ta.learnity.core.datastore.data.repository.UserPreferencesRepository
import com.dak0ta.learnity.core.datastore.domain.usecase.userrole.UpdateUserRoleUseCase
import com.dak0ta.learnity.core.domain.user.UserRole
import javax.inject.Inject

internal class UpdateUserRoleUseCaseImpl @Inject constructor(
    private val repository: UserPreferencesRepository,
) : UpdateUserRoleUseCase {

    override suspend fun invoke(role: UserRole) {
        repository.updateUserRole(role)
    }
}
