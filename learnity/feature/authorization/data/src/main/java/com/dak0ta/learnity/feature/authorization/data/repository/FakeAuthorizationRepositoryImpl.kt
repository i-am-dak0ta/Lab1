package com.dak0ta.learnity.feature.authorization.data.repository

import com.dak0ta.learnity.core.coroutine.CoroutineDispatchers
import com.dak0ta.learnity.core.datastore.domain.usecase.accesstoken.UpdateAccessTokenUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.refreshtoken.UpdateRefreshTokenUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.userid.UpdateUserIdUseCase
import com.dak0ta.learnity.core.datastore.domain.usecase.userrole.UpdateUserRoleUseCase
import com.dak0ta.learnity.core.domain.tutor.Tutor
import com.dak0ta.learnity.core.domain.user.User
import com.dak0ta.learnity.core.domain.user.UserRole
import com.dak0ta.learnity.core.domain.user.UserWithRole
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FakeAuthorizationRepositoryImpl @Inject constructor(
    private val updateAccessTokenUseCase: UpdateAccessTokenUseCase,
    private val updateRefreshTokenUseCase: UpdateRefreshTokenUseCase,
    private val updateUserIdUseCase: UpdateUserIdUseCase,
    private val updateUserRoleUseCase: UpdateUserRoleUseCase,
    private val dispatchers: CoroutineDispatchers,
) : AuthorizationRepository {

    override suspend fun login(input: String, password: String): Unit = withContext(dispatchers.io) {
        delay(1000L)

        val fakeUser = UserWithRole(
            user = User(
                id = "dc56dae5-68fc-426e-ad43-7170132ed952",
                email = "andrei.kuzmin@example.com",
                nickname = null,
                fullName = "Андрей Кузьмин",
                age = 35,
                timeZone = "Europe/Moscow",
                role = UserRole.TUTOR,
                createdAt = "2025-01-10T13:00:00+03:00",
                updatedAt = "2025-10-17T15:18:21.419052+03:00",
            ),
            student = null,
            tutor = Tutor(
                bio = "Учу физике и готовлю к ЕГЭ.",
                subjects = listOf("physics"),
                rating = 4.7,
            ),
        )

        launch { updateAccessTokenUseCase("fake_access_token") }
        launch { updateRefreshTokenUseCase("fake_refresh_token") }
        launch { updateUserIdUseCase(fakeUser.user.id) }
        launch { updateUserRoleUseCase(fakeUser.user.role) }
    }
}
