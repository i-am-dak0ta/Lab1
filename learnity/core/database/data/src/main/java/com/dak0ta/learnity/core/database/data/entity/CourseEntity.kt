package com.dak0ta.learnity.core.database.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.dak0ta.learnity.core.domain.course.CourseLevel

@Entity(
    tableName = "courses",
    foreignKeys = [
        ForeignKey(
            entity = TutorEntity::class,
            parentColumns = ["id"],
            childColumns = ["tutor_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class CourseEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "tutor_id", index = true) val tutorId: String,
    val title: String?,
    val subject: String?,
    val level: CourseLevel,
    val status: String?,
)
