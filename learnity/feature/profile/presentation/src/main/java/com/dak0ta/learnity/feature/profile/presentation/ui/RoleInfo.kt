package com.dak0ta.learnity.feature.profile.presentation.ui

internal sealed interface RoleInfo {

    data class StudentInfo(
        val grade: String,
        val parentContact: String,
    ) : RoleInfo

    data class TutorInfo(
        val bio: String,
        val subjects: String,
        val rating: String,
    ) : RoleInfo

    object None : RoleInfo
}
