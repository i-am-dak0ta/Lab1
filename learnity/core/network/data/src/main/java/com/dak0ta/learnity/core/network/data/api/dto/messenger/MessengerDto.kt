package com.dak0ta.learnity.core.network.data.api.dto.messenger

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class MessengerDto(
    @param:Json(name = "id") val id: String,
)
