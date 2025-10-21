package com.dak0ta.learnity.core.datastore.data.usecase.accesstoken

import com.dak0ta.learnity.core.datastore.data.repository.UserPreferencesRepository
import com.dak0ta.learnity.core.datastore.domain.usecase.accesstoken.GetAccessTokenUseCase
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

internal class GetAccessTokenUseCaseImpl @Inject constructor(
    private val repository: UserPreferencesRepository,
) : GetAccessTokenUseCase {

    override suspend fun invoke(): String? = repository.accessTokenFlow.firstOrNull()
}
