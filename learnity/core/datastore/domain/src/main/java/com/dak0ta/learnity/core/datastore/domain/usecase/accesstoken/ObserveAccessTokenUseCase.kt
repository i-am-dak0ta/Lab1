package com.dak0ta.learnity.core.datastore.domain.usecase.accesstoken

import kotlinx.coroutines.flow.Flow

interface ObserveAccessTokenUseCase {

    operator fun invoke(): Flow<String?>
}
