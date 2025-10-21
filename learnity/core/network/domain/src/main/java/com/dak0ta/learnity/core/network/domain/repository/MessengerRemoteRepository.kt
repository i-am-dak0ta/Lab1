package com.dak0ta.learnity.core.network.domain.repository

import com.dak0ta.learnity.core.domain.messenger.Messenger
import com.dak0ta.learnity.core.network.domain.model.ApiResult

interface MessengerRemoteRepository {

    suspend fun getMessengers(): ApiResult<List<Messenger>>
    suspend fun getMessengerById(id: String): ApiResult<Messenger>
}
