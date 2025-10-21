package com.dak0ta.learnity.core.datastore.data.usecase.refreshtoken

import com.dak0ta.learnity.core.datastore.data.repository.UserPreferencesRepository
import com.dak0ta.learnity.core.datastore.domain.usecase.refreshtoken.GetRefreshTokenUseCase
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

internal class GetRefreshTokenUseCaseImpl @Inject constructor(
    private val repository: UserPreferencesRepository,
) : GetRefreshTokenUseCase {

    override suspend fun invoke(): String? = repository.refreshTokenFlow.firstOrNull()
}
