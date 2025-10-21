package com.dak0ta.learnity.core.network.data.di

import com.dak0ta.learnity.core.network.data.converter.CourseLevelConverter
import com.dak0ta.learnity.core.network.data.converter.UserRoleConverter
import com.dak0ta.learnity.core.network.data.network.ApiLoggingInterceptorProvider
import com.dak0ta.learnity.core.network.data.network.AuthAuthenticator
import com.dak0ta.learnity.core.network.data.network.AuthInterceptor
import com.dak0ta.learnity.core.network.domain.auth.AccessTokenRefresher
import com.dak0ta.learnity.core.network.domain.auth.TokenProvider
import com.dak0ta.learnity.core.network.domain.auth.TokenResetter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [NetworkTokenModule::class, NetworkRepositoryModule::class])
object NetworkModule {

    private const val DEFAULT_BASE_URL = "http://10.0.2.2:8080/api/"

    @Provides
    @Named("baseUrl")
    @Singleton
    fun provideBaseUrl(): String = DEFAULT_BASE_URL

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(UserRoleConverter())
        .add(CourseLevelConverter())
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = ApiLoggingInterceptorProvider().create()

    @Provides
    @Named("plainOkHttp")
    @Singleton
    fun providePlainOkHttpClient(
        logging: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Named("plainRetrofit")
    @Singleton
    fun providePlainRetrofit(
        moshi: Moshi,
        @Named("plainOkHttp") okHttp: OkHttpClient,
        @Named("baseUrl") baseUrl: String,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttp)
            .build()
    }

    @Provides
    @Named("authOkHttp")
    @Singleton
    fun provideAuthOkHttpClient(
        tokenProvider: TokenProvider,
        logging: HttpLoggingInterceptor,
        authenticator: Authenticator,
    ): OkHttpClient {
        val authInterceptor = AuthInterceptor(tokenProvider)
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .addInterceptor(logging)
            .authenticator(authenticator)
            .build()
    }

    @Provides
    @Named("authRetrofit")
    @Singleton
    fun provideAuthRetrofit(
        moshi: Moshi,
        @Named("authOkHttp") okHttp: OkHttpClient,
        @Named("baseUrl") baseUrl: String,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttp)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthAuthenticator(
        refreshHandler: AccessTokenRefresher,
        tokenResetter: TokenResetter,
    ): Authenticator {
        return AuthAuthenticator(refreshHandler, tokenResetter)
    }
}
