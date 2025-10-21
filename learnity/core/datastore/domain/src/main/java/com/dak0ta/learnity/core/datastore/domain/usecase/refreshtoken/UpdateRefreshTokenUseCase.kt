package com.dak0ta.learnity.core.datastore.domain.usecase.refreshtoken

interface UpdateRefreshTokenUseCase {

    suspend operator fun invoke(token: String?)
}
