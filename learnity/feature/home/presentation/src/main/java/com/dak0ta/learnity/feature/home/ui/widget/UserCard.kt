package com.dak0ta.learnity.feature.home.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dak0ta.learnity.feature.home.presentation.ui.RoleInfo
import com.dak0ta.learnity.feature.home.presentation.ui.UserInfo

@Composable
internal fun UserCard(
    user: UserInfo,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(32.dp),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(user.nickname)
            Text(user.fullName)
            Text(user.age)
            Text(user.role)
            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
            when (val info = user.roleInfo) {
                is RoleInfo.StudentInfo -> {
                    Text(info.grade)
                }

                is RoleInfo.TutorInfo -> {
                    Text(info.subjects)
                    Text(info.rating)
                }

                RoleInfo.None -> {}
            }
        }
    }
}
