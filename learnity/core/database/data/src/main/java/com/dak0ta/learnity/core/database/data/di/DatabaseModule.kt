package com.dak0ta.learnity.core.database.data.di

import android.content.Context
import androidx.room.Room
import com.dak0ta.learnity.core.coroutine.CoroutineDispatchers
import com.dak0ta.learnity.core.database.data.cache.CacheManagerImpl
import com.dak0ta.learnity.core.database.data.db.AppDatabase
import com.dak0ta.learnity.core.database.data.repository.CourseLocalRepositoryImpl
import com.dak0ta.learnity.core.database.data.repository.MessengerLocalRepositoryImpl
import com.dak0ta.learnity.core.database.data.repository.UserWithRoleLocalRepositoryImpl
import com.dak0ta.learnity.core.database.domain.cache.CacheManager
import com.dak0ta.learnity.core.database.domain.repository.CourseLocalRepository
import com.dak0ta.learnity.core.database.domain.repository.MessengerLocalRepository
import com.dak0ta.learnity.core.database.domain.repository.UserWithRoleLocalRepository
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors
import javax.inject.Named
import javax.inject.Singleton
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours

@Module
object DatabaseModule {

    private const val DEFAULT_DB_NAME = "learnity.db"

    @Provides
    @Named("dbName")
    @Singleton
    fun provideDbName(): String = DEFAULT_DB_NAME

    @Provides
    @Named("currentTimestampProvider")
    fun provideCurrentTimestampProvider(): () -> Long = { System.currentTimeMillis() }

    @Provides
    @Named("cacheDurationProvider")
    fun provideCacheDurationProvider(): () -> Duration = { 12.hours }

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DEFAULT_DB_NAME)
            .setQueryExecutor(Executors.newSingleThreadExecutor())
            .build()

    @Provides
    fun provideUserWithRoleLocalRepository(
        db: AppDatabase,
    ): UserWithRoleLocalRepository = UserWithRoleLocalRepositoryImpl(
        db = db,
        userDao = db.userDao(),
        studentDao = db.studentDao(),
        tutorDao = db.tutorDao(),
    )

    @Provides
    fun provideMessengerLocalRepository(db: AppDatabase): MessengerLocalRepository =
        MessengerLocalRepositoryImpl(db.messengerDao())

    @Provides
    fun provideCourseLocalRepository(db: AppDatabase): CourseLocalRepository =
        CourseLocalRepositoryImpl(db.courseDao())

    @Provides
    @Singleton
    fun provideCacheManager(
        db: AppDatabase,
        @Named("currentTimestampProvider") currentTs: () -> Long,
        @Named("cacheDurationProvider") cacheDuration: () -> Duration,
        dispatchers: CoroutineDispatchers,
    ): CacheManager = CacheManagerImpl(
        dao = db.cacheTimestampDao(),
        currentTimestampProvider = currentTs,
        cacheDurationProvider = cacheDuration,
        dispatcher = dispatchers,
    )
}
