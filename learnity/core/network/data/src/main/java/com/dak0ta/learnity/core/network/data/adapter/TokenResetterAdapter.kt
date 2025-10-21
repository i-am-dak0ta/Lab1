package com.dak0ta.learnity.core.network.data.adapter

import com.dak0ta.learnity.core.datastore.domain.usecase.ClearAllUseCase
import com.dak0ta.learnity.core.network.domain.auth.TokenResetter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class TokenResetterAdapter @Inject constructor(
    private val clearAllUseCase: ClearAllUseCase,
) : TokenResetter {

    override suspend fun resetToken() = clearAllUseCase()
}
