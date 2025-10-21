package com.dak0ta.learnity.core.database.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dak0ta.learnity.core.database.data.converter.CourseLevelConverter
import com.dak0ta.learnity.core.database.data.converter.JsonListStringConverter
import com.dak0ta.learnity.core.database.data.converter.UserRoleConverter
import com.dak0ta.learnity.core.database.data.dao.CacheTimestampDao
import com.dak0ta.learnity.core.database.data.dao.CourseDao
import com.dak0ta.learnity.core.database.data.dao.MessengerDao
import com.dak0ta.learnity.core.database.data.dao.StudentDao
import com.dak0ta.learnity.core.database.data.dao.TutorDao
import com.dak0ta.learnity.core.database.data.dao.UserDao
import com.dak0ta.learnity.core.database.data.entity.CacheTimestampEntity
import com.dak0ta.learnity.core.database.data.entity.CourseEntity
import com.dak0ta.learnity.core.database.data.entity.MessengerEntity
import com.dak0ta.learnity.core.database.data.entity.StudentEntity
import com.dak0ta.learnity.core.database.data.entity.TutorEntity
import com.dak0ta.learnity.core.database.data.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        TutorEntity::class,
        StudentEntity::class,
        CourseEntity::class,
        MessengerEntity::class,
        CacheTimestampEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(JsonListStringConverter::class, UserRoleConverter::class, CourseLevelConverter::class)
abstract class AppDatabase : RoomDatabase() {

    internal abstract fun userDao(): UserDao
    internal abstract fun tutorDao(): TutorDao
    internal abstract fun studentDao(): StudentDao
    internal abstract fun courseDao(): CourseDao
    internal abstract fun messengerDao(): MessengerDao
    internal abstract fun cacheTimestampDao(): CacheTimestampDao
}
