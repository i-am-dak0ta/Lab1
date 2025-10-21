package com.dak0ta.learnity.core.datastore.data.repository

import androidx.datastore.core.DataStore
import com.dak0ta.learnity.core.datastore.data.crypto.CryptoManager
import com.dak0ta.learnity.core.datastore.data.mapper.domainToProtTheme
import com.dak0ta.learnity.core.datastore.data.mapper.domainToProtoRole
import com.dak0ta.learnity.core.datastore.data.mapper.protoToDomainRole
import com.dak0ta.learnity.core.datastore.data.mapper.protoToDomainTheme
import com.dak0ta.learnity.core.datastore.proto.UserPreferences
import com.dak0ta.learnity.core.domain.AppTheme
import com.dak0ta.learnity.core.domain.user.UserRole
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UserPreferencesRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<UserPreferences>,
    private val cryptoManager: CryptoManager,
) : UserPreferencesRepository {

    override val userIdFlow: Flow<String?> = dataStore.data
        .map { it.userId.ifBlank { null } }
        .distinctUntilChanged()

    override val userRoleFlow: Flow<UserRole> = dataStore.data
        .map { protoToDomainRole(it.userRole) }
        .distinctUntilChanged()

    override val appThemeFlow: Flow<AppTheme> = dataStore.data
        .map { protoToDomainTheme(it.appTheme) }
        .distinctUntilChanged()

    override val accessTokenFlow: Flow<String?> = dataStore.data
        .map { it.accessToken.takeIf(String::isNotBlank)?.let(cryptoManager::decryptFromBase64) }
        .distinctUntilChanged()

    override val refreshTokenFlow: Flow<String?> = dataStore.data
        .map { it.refreshToken.takeIf(String::isNotBlank)?.let(cryptoManager::decryptFromBase64) }
        .distinctUntilChanged()

    override suspend fun updateUserId(id: String?) {
        dataStore.updateData {
            val builder = it.toBuilder()
            if (id == null) builder.clearUserId() else builder.setUserId(id)
            builder.build()
        }
    }

    override suspend fun updateUserRole(role: UserRole) {
        dataStore.updateData {
            it.toBuilder()
                .setUserRole(domainToProtoRole(role))
                .build()
        }
    }

    override suspend fun updateAppTheme(appTheme: AppTheme) {
        dataStore.updateData {
            it.toBuilder()
                .setAppTheme(domainToProtTheme(appTheme))
                .build()
        }
    }

    override suspend fun updateAccessToken(token: String?) {
        val encrypted = token?.let(cryptoManager::encryptToBase64).orEmpty()
        dataStore.updateData { it.toBuilder().setAccessToken(encrypted).build() }
    }

    override suspend fun updateRefreshToken(token: String?) {
        val encrypted = token?.let(cryptoManager::encryptToBase64).orEmpty()
        dataStore.updateData { it.toBuilder().setRefreshToken(encrypted).build() }
    }

    override suspend fun clearAll() {
        dataStore.updateData {
            it.toBuilder()
                .clearUserId()
                .clearUserRole()
                .clearAccessToken()
                .clearRefreshToken()
                .build()
        }
    }
}
