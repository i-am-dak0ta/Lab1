package com.dak0ta.learnity.core.network.data.adapter

import com.dak0ta.learnity.core.datastore.domain.usecase.accesstoken.GetAccessTokenUseCase
import com.dak0ta.learnity.core.network.domain.auth.TokenProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class TokenProviderAdapter @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
) : TokenProvider {

    override suspend fun getAccessToken(): String? = getAccessTokenUseCase()
}
