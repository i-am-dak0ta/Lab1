package com.dak0ta.learnity.core.database.data.converter

import androidx.room.TypeConverter
import com.dak0ta.learnity.core.domain.course.CourseLevel

internal class CourseLevelConverter {

    @TypeConverter
    fun fromCourseLevel(level: CourseLevel): String = level.name.lowercase()

    @TypeConverter
    fun toCourseLevel(level: String): CourseLevel = CourseLevel.valueOf(level.uppercase())
}
