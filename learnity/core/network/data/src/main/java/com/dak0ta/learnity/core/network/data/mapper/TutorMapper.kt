package com.dak0ta.learnity.core.network.data.mapper

import com.dak0ta.learnity.core.domain.tutor.Tutor
import com.dak0ta.learnity.core.network.data.api.dto.tutor.TutorDto

internal fun TutorDto.toDomain(): Tutor = Tutor(
    bio = bio,
    subjects = subjects,
    rating = rating,
)

internal fun Tutor.toDto(): TutorDto = TutorDto(
    bio = bio,
    subjects = subjects,
    rating = rating,
)
