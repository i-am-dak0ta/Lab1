package com.dak0ta.learnity.feature.home.presentation

import com.dak0ta.learnity.core.domain.user.UserWithRole

internal sealed interface HomeState {

    object Loading : HomeState

    data class Content(
        val users: List<UserWithRole>,
        val isRefreshing: Boolean = false,
    ) : HomeState

    object Error : HomeState
}
