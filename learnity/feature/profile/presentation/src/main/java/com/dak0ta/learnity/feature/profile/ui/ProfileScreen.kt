package com.dak0ta.learnity.feature.profile.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dak0ta.learnity.core.navigation.compose.LocalViewModelFactory
import com.dak0ta.learnity.feature.profile.presentation.ProfileViewModel

@Composable
internal fun ProfileScreen() {
    val viewModelFactory = LocalViewModelFactory.current
    val viewModel: ProfileViewModel = viewModel(factory = viewModelFactory)
    val state by viewModel.uiState.collectAsState()

    DisposableEffect(Unit) {
        Log.d("Learnity:ProfileScreen", "Composable created")
        onDispose {
            Log.d("Learnity:ProfileScreen", "Composable disposed")
        }
    }

    LaunchedEffect(Unit) {
        viewModel.initialize()
    }

    ProfileScreenContent(
        state = state,
        onRefresh = viewModel::onRefresh,
        onRetryClick = viewModel::onRetryClick,
    )
}
