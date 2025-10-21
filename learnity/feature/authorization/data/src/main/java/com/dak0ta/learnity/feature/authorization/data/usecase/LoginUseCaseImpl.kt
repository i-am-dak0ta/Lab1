package com.dak0ta.learnity.feature.authorization.data.usecase

import com.dak0ta.learnity.feature.authorization.data.repository.AuthorizationRepository
import com.dak0ta.learnity.feature.authorization.domain.usecase.LoginUseCase
import javax.inject.Inject

internal class LoginUseCaseImpl @Inject constructor(
    private val repository: AuthorizationRepository,
) : LoginUseCase {

    override suspend fun invoke(input: String, password: String) {
        repository.login(input, password)
    }
}
