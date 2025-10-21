package com.dak0ta.learnity.feature.profile.presentation.ui

internal sealed interface ProfileUiState {

    object Loading : ProfileUiState

    data class Content(
        val user: UserInfo,
        val isRefreshing: Boolean,
    ) : ProfileUiState

    data class Error(
        val title: String,
        val description: String,
        val retryButtonText: String,
    ) : ProfileUiState
}
