package com.dak0ta.learnity.feature.authorization.domain.usecase

interface LoginUseCase {

    suspend operator fun invoke(input: String, password: String)
}
