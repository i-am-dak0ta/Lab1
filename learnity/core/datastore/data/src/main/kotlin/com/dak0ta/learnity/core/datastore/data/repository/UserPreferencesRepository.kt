package com.dak0ta.learnity.core.datastore.data.repository

import com.dak0ta.learnity.core.domain.AppTheme
import com.dak0ta.learnity.core.domain.user.UserRole
import kotlinx.coroutines.flow.Flow

internal interface UserPreferencesRepository {

    val userIdFlow: Flow<String?>
    val userRoleFlow: Flow<UserRole>
    val appThemeFlow: Flow<AppTheme>
    val accessTokenFlow: Flow<String?>
    val refreshTokenFlow: Flow<String?>

    suspend fun updateUserId(id: String?)
    suspend fun updateUserRole(role: UserRole)
    suspend fun updateAppTheme(appTheme: AppTheme)
    suspend fun updateAccessToken(token: String?)
    suspend fun updateRefreshToken(token: String?)
    suspend fun clearAll()
}
