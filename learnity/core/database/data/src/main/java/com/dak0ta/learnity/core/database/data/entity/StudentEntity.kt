package com.dak0ta.learnity.core.database.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "students",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class StudentEntity(
    @PrimaryKey val id: String,
    val grade: String?,
    @ColumnInfo(name = "parent_contact") val parentContact: String?,
)
