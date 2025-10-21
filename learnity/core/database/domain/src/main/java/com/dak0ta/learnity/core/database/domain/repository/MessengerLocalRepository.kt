package com.dak0ta.learnity.core.database.domain.repository

import com.dak0ta.learnity.core.domain.messenger.Messenger
import kotlinx.coroutines.flow.Flow

interface MessengerLocalRepository {

    fun observeMessenger(id: String): Flow<Messenger?>
    fun observeAllMessengers(): Flow<List<Messenger>>
    suspend fun getMessengerById(id: String): Messenger?
    suspend fun getAllMessengers(): List<Messenger>
    suspend fun upsertMessenger(messenger: Messenger)
    suspend fun upsertMessengers(messengers: List<Messenger>)
    suspend fun deleteMessenger(id: String)
    suspend fun deleteAllMessengers()
}
