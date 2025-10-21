package com.dak0ta.learnity.core.network.data.repository

import android.util.Log
import com.dak0ta.learnity.core.domain.messenger.Messenger
import com.dak0ta.learnity.core.network.data.api.service.MessengerService
import com.dak0ta.learnity.core.network.data.mapper.toDomain
import com.dak0ta.learnity.core.network.data.network.SafeApiCall
import com.dak0ta.learnity.core.network.domain.model.ApiResult
import com.dak0ta.learnity.core.network.domain.repository.MessengerRemoteRepository

internal class MessengerRemoteRepositoryImpl(
    private val service: MessengerService,
    private val safeApiCall: SafeApiCall,
) : MessengerRemoteRepository {

    override suspend fun getMessengers(): ApiResult<List<Messenger>> {
        Log.d(TAG, "Getting all messengers")
        return safeApiCall("GET_MESSENGERS") { service.getMessengers().map { it.toDomain() } }
    }

    override suspend fun getMessengerById(id: String): ApiResult<Messenger> {
        Log.d(TAG, "Getting messenger by ID: $id")
        return safeApiCall("GET_MESSENGER_BY_ID") { service.getMessengerById(id).toDomain() }
    }

    private companion object {

        const val TAG = "Learnity:MessengerRemoteRepositoryImpl"
    }
}
