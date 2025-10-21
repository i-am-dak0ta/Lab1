package com.dak0ta.learnity.feature.home.ui

import androidx.compose.runtime.Composable
import com.dak0ta.learnity.core.design.ErrorScreen
import com.dak0ta.learnity.core.design.LoadingScreen
import com.dak0ta.learnity.feature.home.presentation.ui.HomeUiState
import com.dak0ta.learnity.feature.home.ui.widget.UserList

@Composable
internal fun HomeScreenContent(
    state: HomeUiState,
    onRefresh: () -> Unit,
    onRetryClick: () -> Unit,
) {
    when (state) {
        is HomeUiState.Loading -> {
            LoadingScreen()
        }

        is HomeUiState.Content -> {
            UserList(state, onRefresh)
        }

        is HomeUiState.Error -> {
            ErrorScreen(
                title = state.title,
                description = state.description,
                retryButtonText = state.retryButtonText,
                onRetryClick = onRetryClick,
            )
        }
    }
}
