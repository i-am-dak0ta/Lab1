package com.dak0ta.learnity.core.datastore.data.usecase.accesstoken

import com.dak0ta.learnity.core.datastore.data.repository.UserPreferencesRepository
import com.dak0ta.learnity.core.datastore.domain.usecase.accesstoken.UpdateAccessTokenUseCase
import javax.inject.Inject

internal class UpdateAccessTokenUseCaseImpl @Inject constructor(
    private val repository: UserPreferencesRepository,
) : UpdateAccessTokenUseCase {

    override suspend fun invoke(token: String?) {
        repository.updateAccessToken(token)
    }
}
