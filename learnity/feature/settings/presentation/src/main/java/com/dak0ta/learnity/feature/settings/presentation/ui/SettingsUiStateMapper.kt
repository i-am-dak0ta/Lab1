package com.dak0ta.learnity.feature.settings.presentation.ui

import com.dak0ta.learnity.core.domain.AppTheme
import com.dak0ta.learnity.feature.settings.presentation.SettingsState
import javax.inject.Inject

internal class SettingsUiStateMapper @Inject constructor() : (SettingsState) -> SettingsUiState {

    override fun invoke(state: SettingsState): SettingsUiState {
        return when (state) {
            is SettingsState.Loading -> SettingsUiState.Loading
            is SettingsState.Content -> mapContentState(state.appTheme)
            is SettingsState.Error -> mapErrorState()
        }
    }

    private fun mapContentState(appTheme: AppTheme): SettingsUiState.Content {
        val themeOptions = listOf(
            ThemeOption(AppTheme.LIGHT, "Светлая", appTheme == AppTheme.LIGHT),
            ThemeOption(AppTheme.DARK, "Тёмная", appTheme == AppTheme.DARK),
            ThemeOption(AppTheme.SYSTEM_DEFAULT, "Системная", appTheme == AppTheme.SYSTEM_DEFAULT),
        )
        return SettingsUiState.Content(themeOptions)
    }

    private fun mapErrorState(): SettingsUiState.Error = SettingsUiState.Error(
        title = "Что-то пошло не так",
        description = "Не удалось загрузить данные. Попробуйте снова позже.",
        retryButtonText = "Попробовать снова",
    )
}
