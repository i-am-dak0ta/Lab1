package com.dak0ta.learnity.core.datastore.domain.usecase.accesstoken

interface GetAccessTokenUseCase {

    suspend operator fun invoke(): String?
}
