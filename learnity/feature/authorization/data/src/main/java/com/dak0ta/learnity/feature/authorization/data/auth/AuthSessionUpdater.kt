package com.dak0ta.learnity.feature.authorization.data.auth

import com.dak0ta.learnity.core.coroutine.asyncAwait
import com.dak0ta.learnity.core.datastore.domain.usecase.accesstoken.UpdateAccessTokenUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.refreshtoken.UpdateRefreshTokenUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.userid.UpdateUserIdUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.userrole.UpdateUserRoleUseCase
import com.dak0ta.learnity.core.domain.user.UserRole
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthSessionUpdater @Inject constructor(
    private val updateAccessTokenUseCase: UpdateAccessTokenUseCase,
    private val updateRefreshTokenUseCase: UpdateRefreshTokenUseCase,
    private val updateUserIdUseCase: UpdateUserIdUseCase,
    private val updateUserRoleUseCase: UpdateUserRoleUseCase,
) {

    fun updateAllAsync(
        scope: CoroutineScope,
        accessToken: String,
        refreshToken: String,
        userId: String,
        userRole: UserRole,
    ) {
        scope.launch {
            asyncAwait(
                s1 = { updateAccessTokenUseCase(accessToken) },
                s2 = { updateRefreshTokenUseCase(refreshToken) },
                s3 = { updateUserIdUseCase(userId) },
                s4 = { updateUserRoleUseCase(userRole) },
            ) { _, _, _, _ -> }
        }
    }
}
