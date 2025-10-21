package com.dak0ta.learnity.core.datastore.domain.usecase.refreshtoken

import kotlinx.coroutines.flow.Flow

interface ObserveRefreshTokenUseCase {

    operator fun invoke(): Flow<String?>
}
