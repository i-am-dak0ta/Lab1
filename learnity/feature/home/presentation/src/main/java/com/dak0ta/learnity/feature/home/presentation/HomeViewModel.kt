package com.dak0ta.learnity.feature.home.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dak0ta.learnity.core.coroutine.runSuspendCatching
import com.dak0ta.learnity.core.mvvm.BaseViewModel
import com.dak0ta.learnity.feature.home.domain.usecase.GetUsersUseCase
import com.dak0ta.learnity.feature.home.domain.usecase.ObserveUsersUseCase
import com.dak0ta.learnity.feature.home.domain.usecase.RefreshUsersUseCase
import com.dak0ta.learnity.feature.home.presentation.ui.HomeUiState
import com.dak0ta.learnity.feature.home.presentation.ui.HomeUiStateMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class HomeViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val refreshUsersUseCase: RefreshUsersUseCase,
    private val observeUsersUseCase: ObserveUsersUseCase,
    uiStateMapper: HomeUiStateMapper,
) : BaseViewModel() {

    private val dataState = MutableStateFlow<HomeState>(HomeState.Loading)
    val uiState: StateFlow<HomeUiState> = dataState.map(uiStateMapper)
        .stateInViewModel(HomeUiState.Loading)

    override fun onFirstInit() {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            runSuspendCatching {
                getUsersUseCase()
            }
                .onSuccess { users ->
                    dataState.value = HomeState.Content(users)
                    observeUsers()
                }
                .onFailure {
                    Log.e(TAG, "Data loading failed", it)
                    dataState.value = HomeState.Error
                }
        }
    }

    private fun observeUsers() {
        viewModelScope.launch {
            observeUsersUseCase().onEach { users ->
                if (users.isNotEmpty()) {
                    dataState.update { currentState ->
                        if (currentState !is HomeState.Content) return@update currentState
                        currentState.copy(users = users)
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
                refreshUsersUseCase()
            }
                .onFailure {
                    Log.e(TAG, "refreshUsersUseCase has failed", it)
                }
            updateContentState { currentState ->
                currentState.copy(isRefreshing = false)
            }
        }
    }

    internal fun onRetryClick() {
        dataState.update { HomeState.Loading }
        loadData()
    }

    private fun updateContentState(block: (HomeState.Content) -> HomeState.Content) {
        dataState.update { oldState ->
            if (oldState is HomeState.Content) {
                block(oldState)
            } else {
                oldState
            }
        }
    }

    private companion object {

        const val TAG = "Learnity:HomeViewModel"
    }
}
