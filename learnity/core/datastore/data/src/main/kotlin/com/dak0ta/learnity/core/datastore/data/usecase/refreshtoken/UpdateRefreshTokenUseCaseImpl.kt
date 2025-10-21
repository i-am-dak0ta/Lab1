package com.dak0ta.learnity.core.datastore.data.usecase.refreshtoken

import com.dak0ta.learnity.core.datastore.data.repository.UserPreferencesRepository
import com.dak0ta.learnity.core.datastore.domain.usecase.refreshtoken.UpdateRefreshTokenUseCase
import javax.inject.Inject

internal class UpdateRefreshTokenUseCaseImpl @Inject constructor(
    private val repository: UserPreferencesRepository,
) : UpdateRefreshTokenUseCase {

    override suspend fun invoke(token: String?) {
        repository.updateRefreshToken(token)
    }
}
