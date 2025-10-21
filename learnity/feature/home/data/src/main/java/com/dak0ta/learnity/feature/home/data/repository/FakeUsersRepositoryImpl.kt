package com.dak0ta.learnity.feature.home.data.repository

import com.dak0ta.learnity.core.domain.student.Student
import com.dak0ta.learnity.core.domain.tutor.Tutor
import com.dak0ta.learnity.core.domain.user.User
import com.dak0ta.learnity.core.domain.user.UserRole
import com.dak0ta.learnity.core.domain.user.UserWithRole
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FakeUsersRepositoryImpl @Inject constructor() : UsersRepository {

    private val fakeUsers = listOf(

        UserWithRole(
            user = User(
                id = "ca1c0c89-2aa6-4a58-9cc8-34c27474428b",
                email = "nikita.romanov@example.com",
                nickname = null,
                fullName = "Никита Романов",
                age = 17,
                timeZone = "Europe/Moscow",
                role = UserRole.STUDENT,
                createdAt = "2025-02-20T13:00:00+03:00",
                updatedAt = "2025-10-17T14:34:09.546072+03:00",
            ),
            student = Student(
                grade = "11",
                parentContact = "+7 900 123 45 67",
            ),
            tutor = null,
        ),
        UserWithRole(
            user = User(
                id = "626737fe-9f29-418f-9e71-67747f847537",
                email = "viktoria.sidorova@example.com",
                nickname = null,
                fullName = "Виктория Сидорова",
                age = 15,
                timeZone = "Europe/Kaliningrad",
                role = UserRole.STUDENT,
                createdAt = "2025-02-25T14:00:00+03:00",
                updatedAt = "2025-10-17T14:34:09.546072+03:00",
            ),
            student = Student(
                grade = "9",
                parentContact = "+7 921 223 11 22",
            ),
            tutor = null,
        ),
        UserWithRole(
            user = User(
                id = "9d4c2b7a-77a5-4b54-a413-927f1c41f34e",
                email = "ivan.petrov@example.com",
                nickname = null,
                fullName = "Иван Петров",
                age = 16,
                timeZone = "Europe/Moscow",
                role = UserRole.STUDENT,
                createdAt = "2025-03-02T10:00:00+03:00",
                updatedAt = "2025-10-17T15:00:00+03:00",
            ),
            student = Student(
                grade = "10",
                parentContact = "+7 930 112 33 44",
            ),
            tutor = null,
        ),
        UserWithRole(
            user = User(
                id = "2a55c9b1-45b7-4a10-bb21-3cf8c7e857d4",
                email = "maria.koroleva@example.com",
                nickname = null,
                fullName = "Мария Королёва",
                age = 14,
                timeZone = "Europe/Samara",
                role = UserRole.STUDENT,
                createdAt = "2025-03-10T11:00:00+04:00",
                updatedAt = "2025-10-17T15:12:00+04:00",
            ),
            student = Student(
                grade = "8",
                parentContact = "+7 987 321 00 99",
            ),
            tutor = null,
        ),
        UserWithRole(
            user = User(
                id = "f5d9c21b-92f1-4e84-bdb7-1a59df45a8a8",
                email = "sofia.ivanova@example.com",
                nickname = null,
                fullName = "София Иванова",
                age = 13,
                timeZone = "Europe/Moscow",
                role = UserRole.STUDENT,
                createdAt = "2025-03-15T09:00:00+03:00",
                updatedAt = "2025-10-17T15:18:21.419052+03:00",
            ),
            student = Student(
                grade = "7",
                parentContact = "+7 915 555 77 88",
            ),
            tutor = null,
        ),
        UserWithRole(
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
        ),
        UserWithRole(
            user = User(
                id = "3f2b9f2a-63b2-4b64-b3af-31b0eac36a81",
                email = "elena.smirnova@example.com",
                nickname = null,
                fullName = "Елена Смирнова",
                age = 42,
                timeZone = "Europe/Samara",
                role = UserRole.TUTOR,
                createdAt = "2024-12-01T09:30:00+04:00",
                updatedAt = "2025-10-17T15:25:00+04:00",
            ),
            student = null,
            tutor = Tutor(
                bio = "Преподаю русский язык и литературу. 20 лет опыта.",
                subjects = listOf("russian", "literature"),
                rating = 4.9,
            ),
        ),
        UserWithRole(
            user = User(
                id = "9adf453e-46cd-4ff2-9f19-743b0c8c6341",
                email = "sergey.morozov@example.com",
                nickname = null,
                fullName = "Сергей Морозов",
                age = 29,
                timeZone = "Europe/Moscow",
                role = UserRole.TUTOR,
                createdAt = "2025-02-01T11:00:00+03:00",
                updatedAt = "2025-10-17T15:28:00+03:00",
            ),
            student = null,
            tutor = Tutor(
                bio = "Объясняю математику просто и доступно. Подготовка к ОГЭ/ЕГЭ.",
                subjects = listOf("math"),
                rating = 4.8,
            ),
        ),
        UserWithRole(
            user = User(
                id = "e1b7cbf4-12a9-4e4e-8cb4-83e6c7d6e87f",
                email = "daria.volkova@example.com",
                nickname = null,
                fullName = "Дарья Волкова",
                age = 31,
                timeZone = "Europe/Kaliningrad",
                role = UserRole.TUTOR,
                createdAt = "2025-01-05T10:15:00+02:00",
                updatedAt = "2025-10-17T15:30:00+02:00",
            ),
            student = null,
            tutor = Tutor(
                bio = "Преподаю английский язык. Использую современные методики.",
                subjects = listOf("english"),
                rating = 4.6,
            ),
        ),
        UserWithRole(
            user = User(
                id = "e3cbe46d-4cc3-4a4e-a96a-5b70c95d8e3f",
                email = "pavel.fedorov@example.com",
                nickname = null,
                fullName = "Павел Фёдоров",
                age = 38,
                timeZone = "Europe/Moscow",
                role = UserRole.TUTOR,
                createdAt = "2025-01-20T08:45:00+03:00",
                updatedAt = "2025-10-17T15:33:00+03:00",
            ),
            student = null,
            tutor = Tutor(
                bio = "Специалист по информатике и программированию. Подготовка к олимпиадам.",
                subjects = listOf("informatics", "programming"),
                rating = 4.9,
            ),
        ),
    )

    override suspend fun getUsers(forceUpdate: Boolean): List<UserWithRole> {
        delay(1000L)
        return fakeUsers
    }

    override fun observeUsersCache(): Flow<List<UserWithRole>> = flow {
        emit(fakeUsers)
    }
}
