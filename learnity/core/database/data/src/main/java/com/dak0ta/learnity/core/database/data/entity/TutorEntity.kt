package com.dak0ta.learnity.core.database.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "tutors",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class TutorEntity(
    @PrimaryKey val id: String,
    val bio: String?,
    val subjects: List<String>?,
    val rating: Double?,
)
