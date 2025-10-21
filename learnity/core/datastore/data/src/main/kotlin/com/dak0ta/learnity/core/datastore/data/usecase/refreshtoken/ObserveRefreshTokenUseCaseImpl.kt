package com.dak0ta.learnity.core.datastore.data.usecase.refreshtoken

import com.dak0ta.learnity.core.datastore.data.repository.UserPreferencesRepository
import com.dak0ta.learnity.core.datastore.domain.usecase.refreshtoken.ObserveRefreshTokenUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ObserveRefreshTokenUseCaseImpl @Inject constructor(
    private val repository: UserPreferencesRepository,
) : ObserveRefreshTokenUseCase {

    override fun invoke(): Flow<String?> = repository.refreshTokenFlow
}
