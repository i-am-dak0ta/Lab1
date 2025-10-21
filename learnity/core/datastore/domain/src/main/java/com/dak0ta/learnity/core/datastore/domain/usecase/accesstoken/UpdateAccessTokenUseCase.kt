package com.dak0ta.learnity.core.datastore.domain.usecase.accesstoken

interface UpdateAccessTokenUseCase {

    suspend operator fun invoke(token: String?)
}
