package com.dak0ta.learnity.core.network.data.di

import com.dak0ta.learnity.core.network.data.adapter.AccessTokenRefresherAdapter
import com.dak0ta.learnity.core.network.data.adapter.TokenProviderAdapter
import com.dak0ta.learnity.core.network.data.adapter.TokenResetterAdapter
import com.dak0ta.learnity.core.network.domain.auth.AccessTokenRefresher
import com.dak0ta.learnity.core.network.domain.auth.TokenProvider
import com.dak0ta.learnity.core.network.domain.auth.TokenResetter
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class NetworkTokenModule {

    @Binds
    @Singleton
    internal abstract fun bindTokenProvider(impl: TokenProviderAdapter): TokenProvider

    @Binds
    @Singleton
    internal abstract fun bindTokenResetter(impl: TokenResetterAdapter): TokenResetter

    @Binds
    @Singleton
    internal abstract fun bindAccessTokenRefresher(impl: AccessTokenRefresherAdapter): AccessTokenRefresher
}
