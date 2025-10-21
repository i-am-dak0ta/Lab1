package com.dak0ta.learnity.core.datastore.data.usecase.accesstoken

import com.dak0ta.learnity.core.datastore.data.repository.UserPreferencesRepository
import com.dak0ta.learnity.core.datastore.domain.usecase.accesstoken.ObserveAccessTokenUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ObserveAccessTokenUseCaseImpl @Inject constructor(
    private val repository: UserPreferencesRepository,
) : ObserveAccessTokenUseCase {

    override fun invoke(): Flow<String?> = repository.accessTokenFlow
}
