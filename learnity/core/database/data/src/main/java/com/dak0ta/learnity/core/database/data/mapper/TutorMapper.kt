package com.dak0ta.learnity.core.database.data.mapper

import com.dak0ta.learnity.core.database.data.entity.TutorEntity
import com.dak0ta.learnity.core.domain.tutor.Tutor

internal fun TutorEntity.toDomain(): Tutor = Tutor(
    bio = bio,
    subjects = subjects,
    rating = rating,
)

internal fun Tutor.toEntity(userId: String): TutorEntity = TutorEntity(
    id = userId,
    bio = bio,
    subjects = subjects,
    rating = rating,
)
