package com.dak0ta.learnity.feature.profile.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dak0ta.learnity.feature.profile.presentation.ui.ProfileUiState
import com.dak0ta.learnity.feature.profile.presentation.ui.RoleInfo

@Composable
internal fun ProfileContent(
    state: ProfileUiState.Content,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    PullToRefreshBox(
        isRefreshing = state.isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(state.user.nickname)
            Text(state.user.fullName)
            Text(state.user.email)
            Text(state.user.age)
            Text(state.user.timeZone)
            Text(state.user.createdAt)
            Text(state.user.role)
            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
            when (val info = state.user.roleInfo) {
                is RoleInfo.StudentInfo -> {
                    Text(info.grade)
                    Text(info.parentContact)
                }

                is RoleInfo.TutorInfo -> {
                    Text(info.bio)
                    Text(info.subjects)
                    Text(info.rating)
                }

                RoleInfo.None -> {}
            }
        }
    }
}
