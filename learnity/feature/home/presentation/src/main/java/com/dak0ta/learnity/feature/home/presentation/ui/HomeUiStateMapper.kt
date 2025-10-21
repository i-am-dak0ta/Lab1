package com.dak0ta.learnity.feature.home.presentation.ui

import com.dak0ta.learnity.core.domain.student.Student
import com.dak0ta.learnity.core.domain.tutor.Tutor
import com.dak0ta.learnity.core.domain.user.UserRole
import com.dak0ta.learnity.core.domain.user.UserWithRole
import com.dak0ta.learnity.feature.home.presentation.HomeState
import javax.inject.Inject

internal class HomeUiStateMapper @Inject constructor() : (HomeState) -> HomeUiState {

    override fun invoke(state: HomeState): HomeUiState {
        return when (state) {
            is HomeState.Loading -> HomeUiState.Loading
            is HomeState.Content -> mapContentState(state)
            is HomeState.Error -> mapErrorState()
        }
    }

    private fun mapContentState(state: HomeState.Content): HomeUiState.Content =
        HomeUiState.Content(
            users = mapUsersToUi(state.users),
            isRefreshing = state.isRefreshing,
        )

    private fun mapUsersToUi(users: List<UserWithRole>): List<UserInfo> {
        return users.map { user ->
            val student: Student? = user.student
            val tutor: Tutor? = user.tutor

            val roleInfo = when {
                student != null -> RoleInfo.StudentInfo(
                    grade = "Класс: ${student.grade ?: "—"}",
                )

                tutor != null -> RoleInfo.TutorInfo(
                    subjects = "Предметы: ${tutor.subjects?.joinToString(", ") ?: "—"}",
                    rating = "Рейтинг: ${tutor.rating ?: "—"}",
                )

                else -> RoleInfo.None
            }

            UserInfo(
                id = user.user.id,
                nickname = "Никнейм: ${user.user.nickname ?: "—"}",
                fullName = "Полное имя: ${user.user.fullName ?: "—"}",
                age = "Возраст: ${user.user.age ?: "—"}",
                role = "Роль: ${mapRoleToString(user.user.role)}",
                roleInfo = roleInfo,
            )
        }
    }

    private fun mapRoleToString(role: UserRole): String {
        return when (role) {
            UserRole.TUTOR -> "Репетитор"
            UserRole.STUDENT -> "Ученик"
            UserRole.ADMIN -> "Админ"
        }
    }

    private fun mapErrorState(): HomeUiState.Error = HomeUiState.Error(
        title = "Что-то пошло не так",
        description = "Не удалось загрузить данные. Попробуйте снова позже.",
        retryButtonText = "Попробовать снова",
    )
}
