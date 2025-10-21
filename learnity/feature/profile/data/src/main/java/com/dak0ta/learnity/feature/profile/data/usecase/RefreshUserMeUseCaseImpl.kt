package com.dak0ta.learnity.feature.profile.data.usecase

import com.dak0ta.learnity.feature.profile.data.repository.UserRepository
import com.dak0ta.learnity.feature.profile.domain.usecase.RefreshUserMeUseCase
import javax.inject.Inject

internal class RefreshUserMeUseCaseImpl @Inject constructor(
    private val repository: UserRepository,
) : RefreshUserMeUseCase {

    override suspend fun invoke() {
        repository.getUserMe(forceUpdate = true)
    }
}
