package com.dak0ta.learnity.feature.home.data.di

import com.dak0ta.learnity.feature.home.data.repository.FakeUsersRepositoryImpl
import com.dak0ta.learnity.feature.home.data.repository.UsersRepository
import com.dak0ta.learnity.feature.home.data.usecase.GetUsersUseCaseImpl
import com.dak0ta.learnity.feature.home.data.usecase.ObserveUsersUseCaseImpl
import com.dak0ta.learnity.feature.home.data.usecase.RefreshUsersUseCaseImpl
import com.dak0ta.learnity.feature.home.domain.usecase.GetUsersUseCase
import com.dak0ta.learnity.feature.home.domain.usecase.ObserveUsersUseCase
import com.dak0ta.learnity.feature.home.domain.usecase.RefreshUsersUseCase
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class HomeDataModule {

    @Binds
    @Singleton
    internal abstract fun bindUsersRepository(
        impl: FakeUsersRepositoryImpl,
    ): UsersRepository

    @Binds
    internal abstract fun bindGetUsersUseCase(
        impl: GetUsersUseCaseImpl,
    ): GetUsersUseCase

    @Binds
    internal abstract fun bindObserveUsersUseCase(
        impl: ObserveUsersUseCaseImpl,
    ): ObserveUsersUseCase

    @Binds
    internal abstract fun bindRefreshUsersUseCase(
        impl: RefreshUsersUseCaseImpl,
    ): RefreshUsersUseCase
}
