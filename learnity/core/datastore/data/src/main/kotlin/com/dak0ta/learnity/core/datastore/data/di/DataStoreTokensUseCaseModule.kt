package com.dak0ta.learnity.core.datastore.data.di

import com.dak0ta.learnity.core.datastore.data.usecase.ClearAllUseCaseImpl
import com.dak0ta.learnity.core.datastore.data.usecase.accesstoken.GetAccessTokenUseCaseImpl
import com.dak0ta.learnity.core.datastore.data.usecase.accesstoken.ObserveAccessTokenUseCaseImpl
import com.dak0ta.learnity.core.datastore.data.usecase.accesstoken.UpdateAccessTokenUseCaseImpl
import com.dak0ta.learnity.core.datastore.data.usecase.refreshtoken.GetRefreshTokenUseCaseImpl
import com.dak0ta.learnity.core.datastore.data.usecase.refreshtoken.ObserveRefreshTokenUseCaseImpl
import com.dak0ta.learnity.core.datastore.data.usecase.refreshtoken.UpdateRefreshTokenUseCaseImpl
import com.dak0ta.learnity.core.datastore.domain.usecase.ClearAllUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.accesstoken.GetAccessTokenUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.accesstoken.ObserveAccessTokenUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.accesstoken.UpdateAccessTokenUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.refreshtoken.GetRefreshTokenUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.refreshtoken.ObserveRefreshTokenUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.refreshtoken.UpdateRefreshTokenUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class DataStoreTokensUseCaseModule {

    @Binds
    internal abstract fun bindClearAllUseCase(
        impl: ClearAllUseCaseImpl,
    ): ClearAllUseCase

    @Binds
    internal abstract fun bindGetAccessTokenUseCase(
        impl: GetAccessTokenUseCaseImpl,
    ): GetAccessTokenUseCase

    @Binds
    internal abstract fun bindGetRefreshTokenUseCase(
        impl: GetRefreshTokenUseCaseImpl,
    ): GetRefreshTokenUseCase

    @Binds
    internal abstract fun bindObserveAccessTokenUseCase(
        impl: ObserveAccessTokenUseCaseImpl,
    ): ObserveAccessTokenUseCase

    @Binds
    internal abstract fun bindObserveRefreshTokenUseCase(
        impl: ObserveRefreshTokenUseCaseImpl,
    ): ObserveRefreshTokenUseCase

    @Binds
    internal abstract fun bindUpdateAccessTokenUseCase(
        impl: UpdateAccessTokenUseCaseImpl,
    ): UpdateAccessTokenUseCase

    @Binds
    internal abstract fun bindUpdateRefreshTokenUseCase(
        impl: UpdateRefreshTokenUseCaseImpl,
    ): UpdateRefreshTokenUseCase
}
