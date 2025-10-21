package com.dak0ta.learnity.feature.home.presentation.ui

internal sealed interface HomeUiState {

    object Loading : HomeUiState

    data class Content(
        val users: List<UserInfo>,
        val isRefreshing: Boolean = false,
    ) : HomeUiState

    data class Error(
        val title: String,
        val description: String,
        val retryButtonText: String,
    ) : HomeUiState
}
