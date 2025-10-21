package com.dak0ta.learnity.feature.profile.presentation

import com.dak0ta.learnity.core.domain.user.UserWithRole

internal sealed interface ProfileState {

    object Loading : ProfileState

    data class Content(
        val userWithRole: UserWithRole,
        val isRefreshing: Boolean = false,
    ) : ProfileState

    object Error : ProfileState
}
