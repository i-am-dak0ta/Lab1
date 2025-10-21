package com.dak0ta.learnity.core.database.data.repository

import com.dak0ta.learnity.core.database.data.dao.MessengerDao
import com.dak0ta.learnity.core.database.data.mapper.toDomain
import com.dak0ta.learnity.core.database.data.mapper.toEntity
import com.dak0ta.learnity.core.database.domain.repository.MessengerLocalRepository
import com.dak0ta.learnity.core.domain.messenger.Messenger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

@Singleton
internal class MessengerLocalRepositoryImpl(
    private val dao: MessengerDao,
) : MessengerLocalRepository {

    override fun observeMessenger(id: String): Flow<Messenger?> {
        return dao.getByIdFlow(id).map { it?.toDomain() }
    }

    override fun observeAllMessengers(): Flow<List<Messenger>> {
        return dao.getAllFlow().map { it.map { e -> e.toDomain() } }
    }

    override suspend fun getMessengerById(id: String): Messenger? {
        return dao.getByIdOnce(id)?.toDomain()
    }

    override suspend fun getAllMessengers(): List<Messenger> {
        return dao.getAllOnce().map { it.toDomain() }
    }

    override suspend fun upsertMessenger(messenger: Messenger) {
        dao.insert(messenger.toEntity())
    }

    override suspend fun upsertMessengers(messengers: List<Messenger>) {
        dao.insertAll(messengers.map { it.toEntity() })
    }

    override suspend fun deleteMessenger(id: String) {
        dao.deleteById(id)
    }

    override suspend fun deleteAllMessengers() {
        dao.deleteAll()
    }
}
