package com.dak0ta.learnity.feature.authorization.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dak0ta.learnity.core.coroutine.runSuspendCatching
import com.dak0ta.learnity.core.datastore.domain.usecase.accesstoken.GetAccessTokenUseCase
import com.dak0ta.learnity.core.mvvm.BaseViewModel
import com.dak0ta.learnity.feature.authorization.domain.usecase.LoginUseCase
import com.dak0ta.learnity.feature.authorization.presentation.ui.AuthorizationUiState
import com.dak0ta.learnity.feature.authorization.presentation.ui.AuthorizationUiStateMapper
import com.dak0ta.learnity.feature.profile.domain.navigation.ProfileDirection
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class AuthorizationViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    uiStateMapper: AuthorizationUiStateMapper,
) : BaseViewModel() {

    private val dataState = MutableStateFlow<AuthorizationState>(AuthorizationState.Loading)
    val uiState: StateFlow<AuthorizationUiState> = dataState.map(uiStateMapper)
        .stateInViewModel(AuthorizationUiState.Loading)
    private val _action = Channel<AuthorizationAction>(Channel.BUFFERED)
    val action: Flow<AuthorizationAction> = _action.receiveAsFlow()

    override fun onFirstInit() {
        checkAuth()
    }

    private fun checkAuth() {
        viewModelScope.launch {
            val token = getAccessTokenUseCase()
            if (!token.isNullOrBlank()) {
                _action.send(AuthorizationAction.NavigateTo(ProfileDirection::class))
            } else {
                runSuspendCatching {
                    loginUseCase(input = "tutor@example.com", password = "qwerty123")
                }
                    .onSuccess {
                        dataState.value = AuthorizationState.Content
                        _action.send(AuthorizationAction.NavigateTo(ProfileDirection::class))
                    }
                    .onFailure { e ->
                        Log.e(TAG, "login has failed")
                        dataState.value = AuthorizationState.Error
                    }
            }
        }
    }

    internal fun onRetryClick() {
        dataState.update { AuthorizationState.Loading }
        checkAuth()
    }

    private companion object {

        const val TAG = "Learnity:AuthorizationViewModel"
    }
}
