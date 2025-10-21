package com.dak0ta.learnity.core.database.data.mapper

import com.dak0ta.learnity.core.database.data.entity.MessengerEntity
import com.dak0ta.learnity.core.domain.messenger.Messenger

internal fun MessengerEntity.toDomain(): Messenger = Messenger(
    id = id,
)

internal fun Messenger.toEntity(): MessengerEntity = MessengerEntity(
    id = id,
)
