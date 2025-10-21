package com.dak0ta.learnity.feature.profile.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dak0ta.learnity.core.coroutine.runSuspendCatching
import com.dak0ta.learnity.core.datastore.domain.usecase.ClearAllUseCase
import com.dak0ta.learnity.core.mvvm.BaseViewModel
import com.dak0ta.learnity.feature.profile.domain.usecase.GetUserMeUseCase
import com.dak0ta.learnity.feature.profile.domain.usecase.ObserveUserMeUseCase
import com.dak0ta.learnity.feature.profile.domain.usecase.RefreshUserMeUseCase
import com.dak0ta.learnity.feature.profile.presentation.ui.ProfileUiState
import com.dak0ta.learnity.feature.profile.presentation.ui.ProfileUiStateMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class ProfileViewModel @Inject constructor(
    private val getUserMeUseCase: GetUserMeUseCase,
    private val refreshUserMeUseCase: RefreshUserMeUseCase,
    private val observeUserMeUseCase: ObserveUserMeUseCase,
    private val clearAllUseCase: ClearAllUseCase,
    uiStateMapper: ProfileUiStateMapper,
) : BaseViewModel() {

    private val dataState = MutableStateFlow<ProfileState>(ProfileState.Loading)
    val uiState: StateFlow<ProfileUiState> = dataState.map(uiStateMapper)
        .stateInViewModel(ProfileUiState.Loading)

    override fun onFirstInit() {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            runSuspendCatching {
                getUserMeUseCase()
            }
                .onSuccess { userWithRole ->
                    dataState.value = ProfileState.Content(userWithRole)
                    observeUser(userWithRole.user.id)
                }
                .onFailure {
                    Log.e(TAG, "Data loading failed", it)
                    dataState.value = ProfileState.Error
                }
        }
    }

    private fun observeUser(id: String) {
        viewModelScope.launch {
            observeUserMeUseCase(id).onEach { userWithRole ->
                if (userWithRole != null) {
                    dataState.update { currentState ->
                        if (currentState !is ProfileState.Content) return@update currentState
                        currentState.copy(userWithRole = userWithRole)
                    }
                }
            }
                .launchIn(this)
        }
    }

    internal fun onRefresh() {
        viewModelScope.launch {
            updateContentState {
                it.copy(isRefreshing = true)
            }
            runSuspendCatching {
                refreshUserMeUseCase()
            }
                .onFailure {
                    Log.e(TAG, "refreshUserMeUseCase has failed", it)
                }
            updateContentState { currentState ->
                currentState.copy(isRefreshing = false)
            }
        }
    }

    internal fun onRetryClick() {
        dataState.update { ProfileState.Loading }
        loadData()
    }

    internal fun logout() {
        viewModelScope.launch {
            clearAllUseCase
        }
    }

    private fun updateContentState(block: (ProfileState.Content) -> ProfileState.Content) {
        dataState.update { oldState ->
            if (oldState is ProfileState.Content) {
                block(oldState)
            } else {
                oldState
            }
        }
    }

    private companion object {

        const val TAG = "Learnity:ProfileViewModel"
    }
}
