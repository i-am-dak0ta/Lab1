package com.dak0ta.learnity.core.network.data.mapper

import com.dak0ta.learnity.core.domain.messenger.Messenger
import com.dak0ta.learnity.core.network.data.api.dto.messenger.MessengerDto

internal fun MessengerDto.toDomain(): Messenger = Messenger(
    id = id,
)

internal fun Messenger.toDto(): MessengerDto = MessengerDto(
    id = id,
)
