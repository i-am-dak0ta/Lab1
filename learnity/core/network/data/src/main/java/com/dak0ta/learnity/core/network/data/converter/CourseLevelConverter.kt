package com.dak0ta.learnity.core.network.data.converter

import com.dak0ta.learnity.core.domain.course.CourseLevel
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

internal class CourseLevelConverter {

    @FromJson
    fun fromJson(value: String?): CourseLevel {
        return value?.lowercase()?.let {
            when (it) {
                "easy" -> CourseLevel.EASY
                "medium" -> CourseLevel.MEDIUM
                "hard" -> CourseLevel.HARD
                else -> CourseLevel.MEDIUM
            }
        } ?: CourseLevel.MEDIUM
    }

    @ToJson
    fun toJson(level: CourseLevel?): String? {
        return level?.let {
            when (it) {
                CourseLevel.EASY -> "easy"
                CourseLevel.MEDIUM -> "medium"
                CourseLevel.HARD -> "hard"
            }
        }
    }
}
