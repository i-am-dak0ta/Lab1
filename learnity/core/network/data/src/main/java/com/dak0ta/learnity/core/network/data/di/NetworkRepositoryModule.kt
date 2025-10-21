package com.dak0ta.learnity.core.network.data.di

import com.dak0ta.learnity.core.coroutine.CoroutineDispatchers
import com.dak0ta.learnity.core.network.data.api.service.AuthService
import com.dak0ta.learnity.core.network.data.api.service.CourseService
import com.dak0ta.learnity.core.network.data.api.service.MessengerService
import com.dak0ta.learnity.core.network.data.api.service.UserService
import com.dak0ta.learnity.core.network.data.network.SafeApiCall
import com.dak0ta.learnity.core.network.data.repository.AuthRemoteRepositoryImpl
import com.dak0ta.learnity.core.network.data.repository.CourseRemoteRepositoryImpl
import com.dak0ta.learnity.core.network.data.repository.MessengerRemoteRepositoryImpl
import com.dak0ta.learnity.core.network.data.repository.UserWithRoleRemoteRepositoryImpl
import com.dak0ta.learnity.core.network.domain.repository.AuthRemoteRepository
import com.dak0ta.learnity.core.network.domain.repository.CourseRemoteRepository
import com.dak0ta.learnity.core.network.domain.repository.MessengerRemoteRepository
import com.dak0ta.learnity.core.network.domain.repository.UserWithRoleRemoteRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkRepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRemoteRepository(
        @Named("plainRetrofit") retrofit: Retrofit,
        dispatchers: CoroutineDispatchers,
    ): AuthRemoteRepository {
        val service = retrofit.create(AuthService::class.java)
        val safeApiCall = SafeApiCall(dispatchers)
        return AuthRemoteRepositoryImpl(service, safeApiCall)
    }

    @Provides
    @Singleton
    fun provideUserRemoteRepository(
        @Named("authRetrofit") retrofit: Retrofit,
        dispatchers: CoroutineDispatchers,
    ): UserWithRoleRemoteRepository {
        val service = retrofit.create(UserService::class.java)
        val safeApiCall = SafeApiCall(dispatchers)
        return UserWithRoleRemoteRepositoryImpl(service, safeApiCall)
    }

    @Provides
    @Singleton
    fun provideMessengerRemoteRepository(
        @Named("authRetrofit") retrofit: Retrofit,
        dispatchers: CoroutineDispatchers,
    ): MessengerRemoteRepository {
        val service = retrofit.create(MessengerService::class.java)
        val safeApiCall = SafeApiCall(dispatchers)
        return MessengerRemoteRepositoryImpl(service, safeApiCall)
    }

    @Provides
    @Singleton
    fun provideCourseRemoteRepository(
        @Named("authRetrofit") retrofit: Retrofit,
        dispatchers: CoroutineDispatchers,
    ): CourseRemoteRepository {
        val service = retrofit.create(CourseService::class.java)
        val safeApiCall = SafeApiCall(dispatchers)
        return CourseRemoteRepositoryImpl(service, safeApiCall)
    }
}
