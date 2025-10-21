package com.dak0ta.learnity.core.datastore.data.di

import com.dak0ta.learnity.core.datastore.data.usecase.theme.GetAppThemeUseCaseImpl
import com.dak0ta.learnity.core.datastore.data.usecase.theme.ObserveAppThemeUseCaseImpl
import com.dak0ta.learnity.core.datastore.data.usecase.theme.UpdateAppThemeUseCaseImpl
import com.dak0ta.learnity.core.datastore.data.usecase.userid.GetUserIdUseCaseImpl
import com.dak0ta.learnity.core.datastore.data.usecase.userid.UpdateUserIdUseCaseImpl
import com.dak0ta.learnity.core.datastore.data.usecase.userrole.GetUserRoleUseCaseImpl
import com.dak0ta.learnity.core.datastore.data.usecase.userrole.UpdateUserRoleUseCaseImpl
import com.dak0ta.learnity.core.datastore.domain.usecase.theme.GetAppThemeUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.theme.ObserveAppThemeUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.theme.UpdateAppThemeUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.userid.GetUserIdUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.userid.UpdateUserIdUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.userrole.GetUserRoleUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.userrole.UpdateUserRoleUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class DataStoreUserUseCaseModule {

    @Binds
    internal abstract fun bindGetAppThemeUseCase(
        impl: GetAppThemeUseCaseImpl,
    ): GetAppThemeUseCase

    @Binds
    internal abstract fun bindGetUserIdUseCase(
        impl: GetUserIdUseCaseImpl,
    ): GetUserIdUseCase

    @Binds
    internal abstract fun bindGetUserRoleUseCase(
        impl: GetUserRoleUseCaseImpl,
    ): GetUserRoleUseCase

    @Binds
    internal abstract fun bindObserveAppThemeUseCase(
        impl: ObserveAppThemeUseCaseImpl,
    ): ObserveAppThemeUseCase

    @Binds
    internal abstract fun bindUpdateAppThemeUseCase(
        impl: UpdateAppThemeUseCaseImpl,
    ): UpdateAppThemeUseCase

    @Binds
    internal abstract fun bindUpdateUserIdUseCase(
        impl: UpdateUserIdUseCaseImpl,
    ): UpdateUserIdUseCase

    @Binds
    internal abstract fun bindUpdateUserRoleUseCase(
        impl: UpdateUserRoleUseCaseImpl,
    ): UpdateUserRoleUseCase
}
