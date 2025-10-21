package com.dak0ta.learnity.feature.profile.presentation.ui

import com.dak0ta.learnity.core.domain.student.Student
import com.dak0ta.learnity.core.domain.tutor.Tutor
import com.dak0ta.learnity.core.domain.user.UserRole
import com.dak0ta.learnity.core.domain.user.UserWithRole
import com.dak0ta.learnity.feature.profile.presentation.ProfileState
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

internal class ProfileUiStateMapper @Inject constructor() : (ProfileState) -> ProfileUiState {

    override fun invoke(state: ProfileState): ProfileUiState {
        return when (state) {
            is ProfileState.Loading -> ProfileUiState.Loading
            is ProfileState.Content -> mapContentState(state)
            is ProfileState.Error -> mapErrorState()
        }
    }

    private fun mapContentState(state: ProfileState.Content): ProfileUiState.Content =
        ProfileUiState.Content(
            user = mapUserToUi(state.userWithRole),
            isRefreshing = state.isRefreshing,
        )

    private fun mapUserToUi(userWithRole: UserWithRole): UserInfo {
        val student: Student? = userWithRole.student
        val tutor: Tutor? = userWithRole.tutor

        val roleInfo = when {
            student != null -> RoleInfo.StudentInfo(
                grade = "Класс: ${student.grade ?: "—"}",
                parentContact = "Контакт родителя: ${student.parentContact ?: "—"}",
            )

            tutor != null -> RoleInfo.TutorInfo(
                bio = "Биография: ${tutor.bio ?: "—"}",
                subjects = "Предметы: ${tutor.subjects?.joinToString(", ") ?: "—"}",
                rating = "Рейтинг: ${tutor.rating ?: "—"}",
            )

            else -> RoleInfo.None
        }

        return UserInfo(
            nickname = "Никнейм: ${userWithRole.user.nickname ?: "—"}",
            fullName = "Полное имя: ${userWithRole.user.fullName ?: "—"}",
            email = "Почта: ${userWithRole.user.email}",
            age = "Возраст: ${userWithRole.user.age ?: "—"}",
            timeZone = "Часовой пояс: ${userWithRole.user.timeZone ?: "—"}",
            createdAt = "Дата создания: ${formatIsoDateToUi(userWithRole.user.createdAt, userWithRole.user.timeZone)}",
            role = "Роль: ${mapRoleToString(userWithRole.user.role)}",
            roleInfo = roleInfo,
        )
    }

    private fun mapRoleToString(role: UserRole): String {
        return when (role) {
            UserRole.TUTOR -> "Репетитор"
            UserRole.STUDENT -> "Ученик"
            UserRole.ADMIN -> "Админ"
        }
    }

    private fun formatIsoDateToUi(dateIso: String, timeZoneId: String?): String {
        return try {
            val sanitized = dateIso.replace(Regex("([+-]\\d\\d):(\\d\\d)"), "$1$2")
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
            val date: Date = parser.parse(sanitized) ?: return "—"

            val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            formatter.timeZone = if (!timeZoneId.isNullOrBlank()) {
                TimeZone.getTimeZone(timeZoneId)
            } else {
                TimeZone.getDefault()
            }

            formatter.format(date)
        } catch (_: ParseException) {
            "—"
        }
    }

    private fun mapErrorState(): ProfileUiState.Error = ProfileUiState.Error(
        title = "Что-то пошло не так",
        description = "Не удалось загрузить данные. Попробуйте снова позже.",
        retryButtonText = "Попробовать снова",
    )
}
