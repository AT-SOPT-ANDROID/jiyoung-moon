package org.sopt.at.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.R

@Composable
fun TopAppBarComponent(
    leftIconId: Int? = null,
    onLeftIconClicked: (() -> Unit) = {},
    rightFirstIconId: Int? = null,
    onRightFirstIconClicked: (() -> Unit) = {},
    rightSecondIconId: Int? = null,
    onRightSecondIconClicked: (() -> Unit) = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color.Black),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 좌측 아이콘
        if (leftIconId != null) {
            Icon(
                painter = painterResource(leftIconId),
                contentDescription = "TopAppBar leftIcon",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onLeftIconClicked() }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // 우측 아이콘 1
        if (rightFirstIconId != null) {
            Icon(
                painter = painterResource(rightFirstIconId),
                contentDescription = "TopAppBar rightFirstIcon",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onRightFirstIconClicked() }
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // 우측 아이콘 2
        if (rightSecondIconId != null) {
            Icon(
                painter = painterResource(rightSecondIconId),
                contentDescription = "TopAppBar rightSecondIconId",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onRightSecondIconClicked() }
            )
        }
    }
}

@Preview
@Composable
fun TopAppBarComponentPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // 1개의 Icon이 있는 경우
        TopAppBarComponent(
            leftIconId = R.drawable.ic_round_arrow_back_ios_24,
            onLeftIconClicked = { /* TODO: 아이콘 클릭 시 처리 */ }
        )

        // 3개의 Icon이 있는 경우
        TopAppBarComponent(
            leftIconId = R.drawable.ic_round_arrow_back_ios_24,
            onLeftIconClicked = { /* TODO: 아이콘 클릭 시 처리 */ },
            rightFirstIconId = R.drawable.ic_round_notifications_none_24,
            onRightFirstIconClicked = { /* TODO: 아이콘 클릭 시 처리 */ },
            rightSecondIconId = R.drawable.ic_outline_settings_24,
            onRightSecondIconClicked = { /* TODO: 아이콘 클릭 시 처리 */ }
        )
    }
}