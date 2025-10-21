package com.dak0ta.learnity.core.database.data.entity

import androidx.room.Embedded
import androidx.room.Relation

internal data class UserWithRoleEntity(
    @Embedded val user: UserEntity,
    @Relation(parentColumn = "id", entityColumn = "id", entity = StudentEntity::class)
    val student: StudentEntity?,
    @Relation(parentColumn = "id", entityColumn = "id", entity = TutorEntity::class)
    val tutor: TutorEntity?,
)
