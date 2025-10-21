package com.dak0ta.learnity.feature.profile.data.repository

import com.dak0ta.learnity.core.domain.tutor.Tutor
import com.dak0ta.learnity.core.domain.user.User
import com.dak0ta.learnity.core.domain.user.UserRole
import com.dak0ta.learnity.core.domain.user.UserWithRole
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FakeUserRepositoryImpl @Inject constructor() : UserRepository {

    private val fakeUserWithRole = UserWithRole(
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

    override suspend fun getUserMe(forceUpdate: Boolean): UserWithRole {
        delay(1000L)
        return fakeUserWithRole
    }

    override fun observeUserMeCache(userId: String): Flow<UserWithRole?> {
        return flowOf(fakeUserWithRole)
    }
}
