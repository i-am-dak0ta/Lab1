package com.dak0ta.learnity.core.datastore.domain.usecase.refreshtoken

interface GetRefreshTokenUseCase {

    suspend operator fun invoke(): String?
}
