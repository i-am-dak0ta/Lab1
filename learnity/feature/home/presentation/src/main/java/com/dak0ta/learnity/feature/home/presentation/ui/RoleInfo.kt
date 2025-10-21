package com.dak0ta.learnity.feature.home.presentation.ui

internal sealed interface RoleInfo {

    data class StudentInfo(val grade: String) : RoleInfo

    data class TutorInfo(
        val subjects: String,
        val rating: String,
    ) : RoleInfo

    object None : RoleInfo
}
