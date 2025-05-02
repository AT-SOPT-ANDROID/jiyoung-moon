package org.sopt.at.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
fun TvingTopAppBarComponent(
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
        Icon(
            painter = painterResource(R.drawable.ic_tving_logo),
            contentDescription = "Tving logo",
            modifier = Modifier.height(24.dp),
            tint = Color.Unspecified
        )

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
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onRightSecondIconClicked() }
            )
        }
    }
}

@Preview
@Composable
fun TvingTopAppBarComponentPreview() {
    TvingTopAppBarComponent(
        rightFirstIconId = R.drawable.ic_baseline_connected_tv_24,
        rightSecondIconId = R.drawable.img_profile
    )
}